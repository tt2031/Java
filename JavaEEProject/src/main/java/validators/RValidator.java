package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validators.RValidator")
public class RValidator implements Validator {
    public RValidator() {
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
        if (Double.isNaN(value)) {
            FacesMessage msg =
                    new FacesMessage("R validation failed.",
                            "R must be numeric");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}