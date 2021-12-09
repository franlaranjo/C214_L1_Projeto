package br.inatel.cdg.DAO;

import br.inatel.cdg.model.Artista;
import br.inatel.cdg.model.ArtistaPalco;
import br.inatel.cdg.model.Palco;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistaPalcoDAOTeste {

    @Mock
    private static ArtistaPalcoDAO dao;

    private static Artista artista = new Artista();
    private static Palco palco = new Palco();
    private static ArtistaPalco artistaPalco =  new ArtistaPalco();
    private static List<ArtistaPalco> artistaPalcoList =  new ArrayList<>();

    @BeforeClass
    public static void preSetup(){

        Palco palco1 =  new Palco();
        palco1.setNome("palco1");
        palco1.setId(1);

        Palco palco2 =  new Palco();
        palco1.setNome("palco2");
        palco1.setId(2);

        Artista art1 = new Artista();
        art1.setId(1);
        art1.setNome("testNome1");
        art1.setGeneroMusical("testGenMus1");

        Artista art2 =  new Artista();
        art2.setId(2);
        art2.setNome("testNome2");
        art2.setGeneroMusical("testGenMus2");

        artistaPalco.setArtista(art1);
        artistaPalco.setPalco(palco1);
        artistaPalco.setHorarioArtista("17:00");

        ArtistaPalco artistapalco1 =  new ArtistaPalco();
        artistapalco1.setArtista(art1);
        artistapalco1.setPalco(palco1);
        artistapalco1.setHorarioArtista("19:00");

        ArtistaPalco artistapalco2 =  new ArtistaPalco();
        artistapalco1.setArtista(art2);
        artistapalco1.setPalco(palco2);
        artistapalco1.setHorarioArtista("20:00");

        artistaPalcoList.add(artistapalco1);
        artistaPalcoList.add(artistapalco2);
    }

    @Test
    public void testeCriarArtistaPalco(){

        when(dao.criar(artistaPalco)).thenReturn(artistaPalco);
        Mockito.doCallRealMethod().when(dao).criar(artistaPalco);

        ArtistaPalco obj = dao.criar(artistaPalco);

        Assert.assertEquals("17:00", obj.getHorarioArtista());
        Assert.assertEquals(0, obj.getId());
    }

    @Test
    public void testeAlterarArtistaPalco(){
        when(dao.atualizar(artistaPalco)).thenReturn(true);

        Assert.assertTrue(dao.atualizar(artistaPalco));
    }

    @Test
    public void testeLerArtistaPalco(){
        when(dao.ler(anyString())).thenReturn(artistaPalcoList);

        List<ArtistaPalco> listaDeObj = dao.ler("testNome4");

        Assert.assertEquals(2,listaDeObj.size());
    }

    @Test
    public void testeLerArtistaPalcoPorId(){
        when(dao.lerPorId(1)).thenReturn(artistaPalcoList.get(0));
        ArtistaPalco obj = dao.lerPorId(1);

        Assert.assertEquals(0,obj.getId());
    }

    @Test
    public void testeDeletarArtistaPalco(){
        when(dao.excluir(1)).thenReturn(true);
        Assert.assertTrue(dao.excluir(1));
    }
}
