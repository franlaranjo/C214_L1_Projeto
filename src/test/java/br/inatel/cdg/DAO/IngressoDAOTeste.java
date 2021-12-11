package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Evento;
import br.inatel.cdg.model.Ingresso;
import br.inatel.cdg.model.Palco;
import br.inatel.cdg.model.Participante;
import br.inatel.cdg.utils.ConversorData;
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
public class IngressoDAOTeste {

    @Mock
    private static IngressoDAO dao;

    private static Ingresso ingresso = new Ingresso();
    private static List<Ingresso> ingressoList = new ArrayList<>();


    @BeforeClass
    public static void preSetup() {
        Palco palco1 = new Palco();
        palco1.setNome("palco1");
        palco1.setId(1);

        Evento eve1 = new Evento();
        eve1.setId(1);
        eve1.setNome("testNome1");
        eve1.setPalco(palco1);
        eve1.setData(ConversorData.converterStringParaLocalDate("01/01/2001"));

        Participante participante = new Participante();
        participante.setNome("Francielly");
        participante.setCPF("000.000.000-00");
        participante.setEmail("fran@gmail.com");
        participante.setTelefone("35 00000-0000");
        participante.setDataNascimento(ConversorData.converterStringParaLocalDate("01/01/2001"));

        ingresso.setEvento(eve1);
        ingresso.setParticipante(participante);
        ingresso.setCategoria("categoria");

        ingressoList.add(ingresso);

        Ingresso ingresso2 = new Ingresso();

        ingresso2.setEvento(eve1);
        ingresso2.setParticipante(participante);
        ingresso2.setCategoria("categoria");

        ingressoList.add(ingresso2);

    }

    @Test
    public void testeCriarEvento() {
        when(dao.criar(ingresso)).thenReturn(ingresso);
        Mockito.doCallRealMethod().when(dao).criar(ingresso);

        Ingresso obj = dao.criar(ingresso);

        Assert.assertNotNull(obj.getCategoria());
        Assert.assertEquals(0, obj.getId());
    }

    @Test
    public void testeAlterarEvento() {
        when(dao.atualizar(ingresso)).thenReturn(true);

        Assert.assertTrue(dao.atualizar(ingresso));
    }

    @Test
    public void testeLerEventos() {
        when(dao.ler(anyString())).thenReturn(ingressoList);

        List<Ingresso> listaDeArtistas = dao.ler("testNome4");

        Assert.assertEquals(2, listaDeArtistas.size());
    }

    @Test
    public void testeLerEventoPorId() {
        when(dao.lerPorId(1)).thenReturn(ingressoList.get(0));
        Ingresso obj = dao.lerPorId(1);

        Assert.assertEquals(obj.getId(), 0);
    }

    @Test
    public void testeDeletarEvento() {
        when(dao.excluir(1)).thenReturn(true);
        Assert.assertTrue(dao.excluir(1));
    }
}
