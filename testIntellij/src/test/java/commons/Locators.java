package commons;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class Locators{

    public String camposFirmasGuardadosBy = "table.table.table-striped > tbody >tr";
    @FindBy(css = ".btn-secondary")
    public static WebElement declineButton;

    @FindBy(css = "div.ar-avatar")
    public static WebElement tramiteElement;

    @FindBy(css = "button.btn-danger")
    public static WebElement publicarButton;

    @FindBy(id = "notify-sticky-wrapper")
    public static WebElement tramitesModal;

    @FindBy(id = "profile-wrapper")
    public static WebElement profileElement;
    private WebAutomator automator;

    @FindBy(css = "input[id ^='fieldCheck']")
    public static List<WebElement> checkFirmaElement;

    @FindBy(xpath = "//input[starts-with(@id,'fieldCheck')]//parent::div//parent::div//parent::label//parent::div//following-sibling::button")
    public static List<WebElement> guardarFirmaElement;

    @FindBy(partialLinkText = "Firma ")
    public static WebElement firmaDigitalTab;

    @FindBy(partialLinkText = "haga click ")
    public static WebElement irATramiteLink;

    @FindBy(linkText = "Disponibles")
    public static WebElement disponiblesLink;

    @FindBy(partialLinkText = "Antecedentes")
    public static WebElement antecedentesTab;

    @FindBy(css = "table.table.table-striped > tbody >tr :nth-child(2) >div")
    public static WebElement camposFirmaGuardadosAntecedentesElement;

    @FindBy(css = "table.table.table-striped > tbody >tr")
    public static WebElement camposFirmaGuardadosFinalElement;

    @FindBy(id = "1")
    public static WebElement nombreNuevoFirmante;

    @FindBy(id = "2")
    public static WebElement rutNuevoFirmante;

    @FindBy(css = "div:nth-child(3) > div:nth-child(1) > button")
    public static WebElement agregarFirmaButton;

    @FindBy(css = "div:nth-child(3) > div:nth-child(2) > button")
    public static WebElement guardarCambiosButton;

    @FindBy(css = "button[disabled]")
    public static WebElement guardarCambiosDisabledButton;

    @FindBy(css = "button.btn.btn-icon-close")
    public static List<WebElement> guardarRutButton;

    @FindBy(css = "svg.fa-save")
    public static List<WebElement> guardarRutAntecedenteButton;

    @FindBy(css = "button.btn.btn-secondary.btn-lg")
    public static WebElement nuevoAntecedenteButton;

    @FindBy(css = "div.table-responsive")
    public static WebElement tablaRutsElement;

    @FindBy(css = "table > tbody > tr:nth-child(2) > th:nth-child(2) > div > select")
    public static WebElement mandatarioSelect;

    @FindBy(css = "table > tbody > tr:nth-child(1) > th:nth-child(2) > div > select")
    public static WebElement mandanteSelect;

    @FindBy(css = "div.input-group> input[name='search']")
    public static WebElement buscarTramiteElement;

    @FindBy(css = "div.input-group> input[name='search'] + div >button")
    public static WebElement buscarTramiteButton;

    @FindBy(css = "div:nth-child(3) > div:nth-child(2) > div.icon > a")
    public static WebElement subirDocumentoButton;

    @FindBy(id = "requestNewFile")
    public static WebElement subirDocumentoElement;

    @FindBy(css = ".item > button")
    public static WebElement enviarButton;

    @FindBy(css = "div.alert-success")
    public static WebElement alertaExitosaEnvioElement;

    @FindBy(css = ".ar-buttonarea>button[type='button']")
    public static WebElement asginarTramiteButton;

    @FindBy(css = ".maar-alerts>.alert-success")
    public static WebElement tramiteAsignadoExitoElement;

    public Locators(WebAutomator automator) throws Exception {
        PageFactory.initElements(automator.getDriver(), this);
    }


}
