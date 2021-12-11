package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Artista;
import br.inatel.cdg.model.Palco;
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
public class PalcoDAOTeste {

    @Mock
    private static PalcoDAO dao;

    private static Palco palco = new Palco();
    private static List<Palco> palcoList = new ArrayList<>();


    @BeforeClass
    public static void preSetup() {

        palco.setId(1);
        palco.setNome("testNome1");
        palcoList.add(palco);

        Palco palco2 = new Palco();
        palco2.setId(2);
        palco2.setNome("testNome2");
        palcoList.add(palco2);
    }

    @Test
    public void testeCriarPalco() {

        when(dao.criar(palco)).thenReturn(palco);
        Mockito.doCallRealMethod().when(dao).criar(palco);

        Palco obj = dao.criar(palco);

        Assert.assertNotNull(obj.getNome());
        Assert.assertEquals(0, obj.getId());
    }

    @Test
    public void testeAlterarPalco() {
        when(dao.atualizar(palco)).thenReturn(true);

        Assert.assertTrue(dao.atualizar(palco));
    }

    @Test
    public void testeLerPalcos() {
        when(dao.ler(anyString())).thenReturn(palcoList);

        List<Palco> listaDeObj = dao.ler("testNome4");

        Assert.assertEquals(2, listaDeObj.size());
    }

    @Test
    public void testeLerPalcoPorId() {
        when(dao.lerPorId(0)).thenReturn(palcoList.get(0));
        Palco obj = dao.lerPorId(0);

        Assert.assertEquals(obj.getId(), 0);
    }

    @Test
    public void testeDeletarPalco() {
        when(dao.excluir(1)).thenReturn(true);
        Assert.assertTrue(dao.excluir(1));
    }
}
