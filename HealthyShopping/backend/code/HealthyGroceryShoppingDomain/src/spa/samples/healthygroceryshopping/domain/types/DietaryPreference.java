/**
 * 
 */
package spa.samples.healthygroceryshopping.domain.types;

/**
 * This record represents dietary preferences.
 * 
 * A dietary preference concerns a <code>FoodProduct</code>, and
 * is represented by a <code>PreferenceModality</code>, which
 * is anywhere from "I can't stand it", to "I can't get
 * enough of it" (check <code>PreferenceModality</code>
 * 
 * Author: Ghizlane Elboussaidi & Hafedh Mili
 */
public record DietaryPreference(FoodProduct foodProduct, PreferenceModality preference) {
}
