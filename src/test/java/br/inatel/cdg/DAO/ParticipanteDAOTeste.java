package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Palco;
import br.inatel.cdg.model.Participante;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParticipanteDAOTeste {

    @Mock
    private static ParticipanteDAO dao;

    private static Participante participante = new Participante();
    private static List<Participante> participanteList = new ArrayList<>();


    @BeforeClass
    public static void preSetup() {

        participante.setId(1);
        participante.setNome("testNome1");
        participanteList.add(participante);

        Participante participante2 = new Participante();
        participante2.setId(2);
        participante2.setNome("testNome2");
        participanteList.add(participante2);
    }

    @Test
    public void testeCriarParticipante() {

        when(dao.criar(participante)).thenReturn(participante);
        Mockito.doCallRealMethod().when(dao).criar(participante);

        Participante obj = dao.criar(participante);

        Assert.assertNotNull(obj.getNome());
        Assert.assertEquals(0, obj.getId());
    }

    @Test
    public void testeAlterarParticipante() {
        when(dao.atualizar(participante)).thenReturn(true);

        Assert.assertTrue(dao.atualizar(participante));
    }

    @Test
    public void testeLerParticipante() {
        when(dao.ler(anyString())).thenReturn(participanteList);

        List<Participante> listaDeObj = dao.ler("testNome4");

        Assert.assertEquals(2, listaDeObj.size());
    }

    @Test
    public void testeLerParticipantePorId() {
        when(dao.lerPorId(0)).thenReturn(participanteList.get(0));
        Participante obj = dao.lerPorId(0);

        Assert.assertEquals(obj.getId(), 0);
    }

    @Test
    public void testeDeletarParticipante() {
        when(dao.excluir(1)).thenReturn(true);
        Assert.assertTrue(dao.excluir(1));
    }
}
