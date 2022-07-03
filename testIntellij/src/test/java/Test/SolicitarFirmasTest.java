package Test;


import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;



public class SolicitarFirmasTest extends BaseTest {
    private String tramiteid = "150250";
    private String tramitedoc = "poder-simple-150219.docx";

    @Test (priority = 1)
    @Severity(SeverityLevel.CRITICAL)


    public void seleccionarTramite() throws Exception {
        //assertTrue(solicitarfirmas.asignarTramite(tramiteid));
        assertTrue(solicitarfirmas.seleccionarTramite(tramiteid));

    }

    @Test(priority = 2)
    @Severity(SeverityLevel.CRITICAL)

    public void seleccionarCamposFirma() throws Exception {
        assertTrue(solicitarfirmas.seleccionarCamposFirma());
    }

    @Test(priority = 3)
    @Severity(SeverityLevel.CRITICAL)

    public void nuevaFirma() throws Exception {
        assertTrue(solicitarfirmas.agregarNuevaFirma());
    }

    @Test(priority = 4, enabled = false)
    @Severity(SeverityLevel.CRITICAL)

    public void subirDoc() throws Exception {
        assertTrue(solicitarfirmas.subirDocumento(tramitedoc));
    }


}