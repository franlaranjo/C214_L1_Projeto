package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Artista;
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
public class ArtistaDAOTeste {

    @Mock
    private static ArtistaDAO dao;

    private static Artista artista = new Artista();
    private static List<Artista> artistaList = new ArrayList<>();


    @BeforeClass
    public static void preSetup() {

        Artista art1 = new Artista();
        art1.setId(1);
        art1.setNome("testNome1");
        art1.setGeneroMusical("testGenMus1");
        artistaList.add(art1);

        Artista art2 = new Artista();
        art2.setId(2);
        art2.setNome("testNome2");
        art2.setGeneroMusical("testGenMus2");
        artistaList.add(art2);
    }

    @Test
    public void testeCriarArtista() {
        artista.setNome("testNome3");
        artista.setGeneroMusical("testGenMus3");

        when(dao.criar(artista)).thenReturn(artista);
        Mockito.doCallRealMethod().when(dao).criar(artista);

        Artista obj = dao.criar(artista);

        Assert.assertNotNull(obj.getNome());
        Assert.assertEquals(0, obj.getId());
    }

    @Test
    public void testeAlterarArtista() {
        artista.setId(0);
        artista.setNome("testNome4");
        artista.setGeneroMusical("testNome4");

        when(dao.atualizar(artista)).thenReturn(true);

        Assert.assertTrue(dao.atualizar(artista));
    }

    @Test
    public void testeLerArtistas() {
        when(dao.ler(anyString())).thenReturn(artistaList);

        List<Artista> listaDeObj = dao.ler("testNome4");

        Assert.assertEquals(2, listaDeObj.size());
    }

    @Test
    public void testeLerArtistaPorId() {
        when(dao.lerPorId(1)).thenReturn(artistaList.get(0));
        Artista obj = dao.lerPorId(1);

        Assert.assertEquals(obj.getId(), 1);
    }

    @Test
    public void testeDeletarArtista() {
        when(dao.excluir(1)).thenReturn(true);
        Assert.assertTrue(dao.excluir(1));
    }
}
