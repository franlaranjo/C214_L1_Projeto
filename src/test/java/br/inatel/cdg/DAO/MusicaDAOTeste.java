package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Artista;
import br.inatel.cdg.model.Musica;
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
public class MusicaDAOTeste {

    @Mock
    private static MusicaDAO dao;

    private static Artista artista = new Artista();
    private static Musica musica = new Musica();
    private static List<Musica> musicaList = new ArrayList<>();


    @BeforeClass
    public static void preSetup() {

        Artista art1 = new Artista();
        art1.setId(1);
        art1.setNome("testNome1");
        art1.setGeneroMusical("testGenMus1");

        musica.setNome("testNome1");
        musica.setArtista(art1);
        musica.setId(0);

        Musica mus2 =  new Musica();
        mus2.setNome("testNome1");
        mus2.setArtista(art1);

        musicaList.add(musica);
        musicaList.add(mus2);
    }

    @Test
    public void testeCriarMusica() {
        when(dao.criar(musica)).thenReturn(musica);
        Mockito.doCallRealMethod().when(dao).criar(musica);

        Musica obj = dao.criar(musica);

        Assert.assertNotNull(obj.getNome());
        Assert.assertEquals(0, obj.getId());
    }

    @Test
    public void testeAlterarMusica() {
        when(dao.atualizar(musica)).thenReturn(true);

        Assert.assertTrue(dao.atualizar(musica));
    }

    @Test
    public void testeLerMusicas() {
        when(dao.ler(anyString())).thenReturn(musicaList);

        List<Musica> listaDeObj = dao.ler("testNome4");

        Assert.assertEquals(2, listaDeObj.size());
    }

    @Test
    public void testeLerMusicaPorId() {
        when(dao.lerPorId(0)).thenReturn(musica);
        Musica obj = dao.lerPorId(0);

        Assert.assertEquals(0, obj.getId());
    }

    @Test
    public void testeDeletarMusica() {
        when(dao.excluir(1)).thenReturn(true);
        Assert.assertTrue(dao.excluir(1));
    }
}
