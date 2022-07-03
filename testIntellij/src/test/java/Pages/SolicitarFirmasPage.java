package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import commons.Configuration;
import commons.Locators;
import commons.WebAutomator;

public class SolicitarFirmasPage extends Locators {
    private WebAutomator automator;
    private int cantidadCamposFirmaAntecedente = 0;
    private int cantidadCamposFirmaFinal = 0;

    public SolicitarFirmasPage(WebAutomator automator) throws Exception {
        super(automator);
        this.automator = automator;
    }

    public boolean asignarTramite(String tramiteId) throws Exception {
        automator.waitVisibilityOf(disponiblesLink, Configuration.timeout);
        automator.clickJs(disponiblesLink);
        automator.waitVisibilityOf(buscarTramiteElement, Configuration.timeout);
        automator.typeClear(buscarTramiteElement, tramiteId);
        automator.clickJs(buscarTramiteButton);
        automator.waitElementToBeClickable(asginarTramiteButton, Configuration.timeout);
        automator.clickJs(asginarTramiteButton);
        if (automator.waitVisibilityOf(tramiteAsignadoExitoElement, Configuration.timeout) != null) {
            automator.clickJs(irATramiteLink);
            return true;
        } else {
            return false;
        }
    }

    public boolean seleccionarTramite(String tramiteId) throws Exception {
        if (automator.waitVisibilityOf(declineButton, Configuration.timeout) != null) {
            automator.clickJs(declineButton);
        }
        automator.waitVisibilityOf(buscarTramiteElement, Configuration.timeout);
        automator.type(buscarTramiteElement, tramiteId);
        automator.clickJs(buscarTramiteButton);
        automator.waitVisibilityOf(tramiteElement, Configuration.timeout);
        automator.clickJs(Locators.tramiteElement);
        if (automator.waitVisibilityOf(publicarButton, Configuration.timeout) != null) {
            return true;
        } else {
            return false;
        }

    }

    public boolean seleccionarCamposFirma() throws Exception {
        automator.waitVisibilityOf(nuevoAntecedenteButton, Configuration.timeout);
        if ((!automator.isSelected(automator.getIndx(checkFirmaElement, 0)))
                && (!automator.isSelected(automator.getIndx(checkFirmaElement, 6)))) {
            automator.clickJs(automator.getIndx(checkFirmaElement, 0));
            automator.clickJs(automator.getIndx(guardarFirmaElement, 0));
            automator.waitVisibilityOf(automator.getIndx(guardarFirmaElement, 5), Configuration.timeout);
            automator.clickJs(automator.getIndx(checkFirmaElement, 5));
            automator.clickJs(automator.getIndx(guardarFirmaElement, 5));
        }
        automator.clickJs(firmaDigitalTab);
        if (automator.waitVisibilityOf(camposFirmaGuardadosFinalElement, Configuration.timeout) != null) {
            asignarRutFirmantes();
            return true;
        } else {
            return false;
        }
    }

    public boolean asignarRutFirmantes() throws Exception {
        if (automator.waitVisibilityOf(guardarRutAntecedenteButton, Configuration.timeout) != null) {
            System.out.println("ENTRE ACA: " + guardarRutButton);
            Select rutMandanteSelector = new Select(mandanteSelect);
            rutMandanteSelector.selectByValue(Configuration.RUTMANDANTE);
            automator.clickJs(guardarRutButton.get(0));
            automator.waitVisibilityOf(tablaRutsElement, Configuration.timeout);
            Select rutMandatarioSelector = new Select(mandatarioSelect);
            rutMandatarioSelector.selectByValue(Configuration.RUTMANDATARIO);
            automator.clickJs(guardarRutButton.get(0));
        } else {
            Configuration.LOGGER.debug("CAMPOS YA TIENEN RUT ASIGNADOS");
        }
        return true;
    }

    public boolean agregarNuevaFirma() throws Exception {
        automator.waitVisibilityOf(agregarFirmaButton,Configuration.timeout);
        cantidadCamposFirmaAntecedente = automator.SizeElements(By.cssSelector(camposFirmasGuardadosBy));
        automator.type(nombreNuevoFirmante, Configuration.NOMBRENUEVOFIRMANTE);
        automator.type(rutNuevoFirmante, Configuration.RUTNUEVOFIRMANTE);
        automator.clickJs(agregarFirmaButton);
        automator.waitElementToBeClickable(guardarCambiosButton, Configuration.timeout);
        automator.clickJs(guardarCambiosButton);
        automator.waitVisibilityOf(guardarCambiosDisabledButton, Configuration.timeout);
        cantidadCamposFirmaFinal = automator.SizeElements(By.cssSelector(camposFirmasGuardadosBy));
        System.out.println(cantidadCamposFirmaAntecedente + " y " + cantidadCamposFirmaFinal);
        if (cantidadCamposFirmaAntecedente < cantidadCamposFirmaFinal) {
            return true;
        } else {
            return false;
        }
    }

    public boolean subirDocumento(String archivo) throws Exception {
        automator.waitVisibilityOf(subirDocumentoButton, Configuration.timeout);
        automator.type(subirDocumentoElement, Configuration.uploadTramiteFilePath + archivo);
        automator.waitVisibilityOf(enviarButton, Configuration.timeout);
        automator.clickJs(enviarButton);
        if (automator.waitVisibilityOf(alertaExitosaEnvioElement, Configuration.timeout) != null) {
            return true;

        } else {
            return false;
        }
    }

}
