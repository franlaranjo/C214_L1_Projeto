package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Artista;
import br.inatel.cdg.model.Evento;
import br.inatel.cdg.model.Palco;
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
public class EventoDAOTeste {

    @Mock
    private static EventoDAO dao;

    private static Evento evento = new Evento();
    private static Palco palco = new Palco();
    private static List<Evento> eventoList = new ArrayList<>();


    @BeforeClass
    public static void preSetup() {
        Palco palco1 =  new Palco();
        palco1.setNome("palco1");
        palco1.setId(1);

        palco.setId(2);
        palco.setNome("palco2");

        evento.setPalco(palco);
        evento.setNome("testNome0");
        evento.setData(ConversorData.converterStringParaLocalDate("01/01/2001"));

        Evento eve1 = new Evento();
        eve1.setId(1);
        eve1.setNome("testNome1");
        eve1.setPalco(palco1);
        eve1.setData(ConversorData.converterStringParaLocalDate("01/01/2001"));

        eventoList.add(eve1);

        Evento eve2 = new Evento();
        eve2.setId(2);
        eve2.setNome("testNome2");
        eve2.setPalco(palco1);
        eve2.setData(ConversorData.converterStringParaLocalDate("01/01/2001"));
        eventoList.add(eve2);
    }

    @Test
    public void testeCriarEvento() {
        when(dao.criar(evento)).thenReturn(evento);
        Mockito.doCallRealMethod().when(dao).criar(evento);

        Evento obj = dao.criar(evento);

        Assert.assertNotNull(obj.getNome());
        Assert.assertEquals(0, obj.getId());
    }

    @Test
    public void testeAlterarEvento() {
        when(dao.atualizar(evento)).thenReturn(true);

        Assert.assertTrue(dao.atualizar(evento));
    }

    @Test
    public void testeLerEventos() {
        when(dao.ler(anyString())).thenReturn(eventoList);

        List<Evento> listaDeObj = dao.ler("testNome4");

        Assert.assertEquals(2, listaDeObj.size());
    }

    @Test
    public void testeLerEventoPorId() {
        when(dao.lerPorId(1)).thenReturn(eventoList.get(0));
        Evento  obj = dao.lerPorId(1);

        Assert.assertEquals(obj.getId(), 1);
    }

    @Test
    public void testeDeletarEvento() {
        when(dao.excluir(1)).thenReturn(true);
        Assert.assertTrue(dao.excluir(1));
    }
}
