package annotation;

import javax.validation.groups.Default;

public final class ValidationGroups {
    private ValidationGroups(){}

    //public interface 이름 extends Default {};
    public interface signUp extends Default {};
    public interface logIn extends Default {};

}
