package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validators.YValidator")
public class YValidator implements Validator {

    public YValidator() {
    }

    /*Не пашет на хелиосе*/
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String v = o.toString();
        v = v.replace(',', '.');
        double value = Double.NaN;
        try {
            value = Double.parseDouble(v);
        } catch (NumberFormatException ignored) {
        }
        if (Double.isNaN(value) || value > 3 || value < -5) {
            FacesMessage msg =
                    new FacesMessage("Y validation failed.",
                            "Y must be in {-5;3}");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
