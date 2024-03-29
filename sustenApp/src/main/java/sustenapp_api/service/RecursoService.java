package sustenapp_api.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sustenapp_api.component.dependency.DateDependency;
import sustenapp_api.component.rule.Validation;
import sustenapp_api.component.validation.*;
import sustenapp_api.dto.POST.RecursoDto;
import sustenapp_api.dto.PUT.RecursoPutDto;
import sustenapp_api.exception.BadRequestException;
import sustenapp_api.exception.ExceptionGeneric;
import sustenapp_api.exception.NotFoundException;
import sustenapp_api.mapper.RecursoMapper;
import sustenapp_api.model.persist.RecursoModel;
import sustenapp_api.repository.RecursoRepository;
import sustenapp_api.repository.TarifaRepository;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RecursoService implements Validation<RecursoDto, RecursoPutDto>  {
    private final RecursoRepository recursoRepository;
    private final TarifaRepository tarifaRepository;
    private final RecursoMapper recursoMapper;
    private final UsuarioExists usuarioValidation;
    private final TarifaExists tarifaValidation;
    private final RecursoValid recursoValid;

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public RecursoModel save(RecursoDto recursoDto){
        validatedPost(recursoDto);

        var recurso = recursoMapper.toMapper(recursoDto);
        //verifyDate(recurso);
        return recursoRepository.save(recurso);
    }

    @Transactional(rollbackOn = ExceptionGeneric.class)
    public void delete(UUID recurso){
        try {
            recursoRepository.deleteById(recurso);
        } catch (Exception ignored) { }
    }

    public RecursoModel update(RecursoPutDto recurso){
        validatePut(recurso);
        return recursoRepository.save(recursoMapper.toMapper(recurso, findById(recurso.getId())));
    }

    public RecursoModel findById(UUID recurso){
        return recursoRepository.findById(recurso).map(this::getFull).orElseThrow(
                () -> new NotFoundException("RECURSO")
        );
    }

    public List<RecursoModel> listAllByUsuario(UUID usuario) {
        return recursoRepository.findAllByUsuario(usuario)
                .orElseThrow(() -> new NotFoundException("RECURSO"))
                .stream().map(this::getFull).toList();
    }

    private RecursoModel getFull(RecursoModel recurso){
        recurso.setValorEstimado(
                recurso.getConsumo() * tarifaRepository.findById(recurso.getTarifa()).get().getPreco()
        );

        recurso.setMediaConsumoDiario(
                recurso.getConsumo() / DateDependency.getDate().getDayOfMonth()
        );

        recurso.setMediaConsumoSemanal(
                recurso.getConsumo() / DateDependency.getDateCalendar().get(Calendar.WEEK_OF_MONTH)
        );

        return recurso;
    }

    public RecursoModel findLast(UUID usuario, String tipo, boolean renovavel) {
        var ultimoRecurso = recursoRepository.findLast(usuario, tipo, renovavel).orElseThrow(
                () -> new NotFoundException("RECURSO")
        );

        return (
                DateDependency.isEqualMonthAndYear(ultimoRecurso.getData(), DateDependency.getDate()) ?
                        ultimoRecurso : recursoRepository.save(clone(ultimoRecurso))
        );
    }

    private RecursoModel clone(RecursoModel recurso) {
        recurso.setId(null);
        recurso.setData(DateDependency.getDate());

        return recurso;
    }

    private void verifyDate(RecursoModel recurso) {
        if(!(checkDate(recurso)))
            throw new BadRequestException("RECURSO");
    }

    private boolean checkDate(RecursoModel recurso) {
        return DateDependency.checkDate(recurso.getData());
    }

    @Override
    public boolean validatePost(RecursoDto value) {
        return Stream.of(
                NotNull.isValid(value.isRenovavel()),
                tarifaValidation.isValid(value.getTarifa()),
                NotNull.isValid(value.getTipo()),
                NotEmpty.isValid(value.getTipo()),
                Size.isValid(value.getTipo(), 7, 8),
                NotNull.isValid(value.getUsuario()),
                usuarioValidation.isValid(value.getUsuario()),
                recursoValid.isValid(value.getUsuario(), value.getTipo())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPost(RecursoDto value) {
        if(!validatePost(value))
            throw new BadRequestException("RECURSO");
    }

    @Override
    public boolean validatePut(RecursoPutDto value) {
        return Stream.of(
                NotNull.isValid(value.isRenovavel()),
                NotNull.isValid(value.getId()),
                tarifaValidation.isValid(value.getTarifa())
        ).allMatch(valor -> valor.equals(true));
    }

    @Override
    public void validatedPut(RecursoPutDto value) {
        if(!validatePut(value))
            throw new BadRequestException("RECURSO");
    }
}
