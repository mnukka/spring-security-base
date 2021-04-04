package ee.company.crm.domain.service.user.profile.property;


/**
 * This interface gives us the ability to decouple from Spring beans if necessary.
 * Also helps writing a bit clearer code in the long run
 * as we are avoiding {@code List<Class<? extends ProfileProperty>>} in tests and PropertyManager
 */
public interface PropertyInstance {
    ProfileProperty get();
}
