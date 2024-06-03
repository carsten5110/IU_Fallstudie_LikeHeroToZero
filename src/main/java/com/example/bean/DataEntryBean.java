package com.example.bean;

import com.example.dao.PendingEmissionDAO;
import com.example.model.PendingEmission;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Named
@RequestScoped
public class DataEntryBean {

    private String country;
    private int year;
    private String emissions;
    private List<String> countries;

    @Inject
    private PendingEmissionDAO pendingEmissionDAO;

    @Inject
    private LoginBean loginBean;

    public DataEntryBean() {
        // Initialisiere die Liste der Länder
        countries = Arrays.asList(
                "Aruba", "Afghanistan", "Angola", "Albania", "Andorra",
                "United Arab Emirates", "Argentina", "Armenia", "American Samoa",
                "Antigua and Barbuda", "Australia", "Austria", "Azerbaijan", "Burundi",
                "Belgium", "Benin", "Burkina Faso", "Bangladesh", "Bulgaria", "Bahrain",
                "Bahamas, The", "Bosnia and Herzegovina", "Belarus", "Belize", "Bermuda",
                "Bolivia", "Brazil", "Barbados", "Brunei Darussalam", "Bhutan", "Botswana",
                "Canada", "Switzerland", "Chile", "China", "Cote d'Ivoire", "Cameroon",
                "Congo, Dem. Rep.", "Congo, Rep.", "Colombia", "Comoros", "Cabo Verde",
                "Costa Rica", "Cuba", "Curacao", "Cayman Islands", "Cyprus", "Czechia",
                "Germany", "Djibouti", "Dominica", "Denmark", "Dominican Republic",
                "Algeria", "Ecuador", "Egypt, Arab Rep.", "Eritrea", "Spain", "Estonia",
                "Ethiopia", "Finland", "Fiji", "France", "Faroe Islands", "Micronesia, Fed. Sts.",
                "Gabon", "United Kingdom", "Georgia", "Ghana", "Gibraltar", "Guinea",
                "Gambia, The", "Guinea-Bissau", "Equatorial Guinea", "Greece", "Grenada",
                "Greenland", "Guatemala", "Guam", "Guyana", "Hong Kong SAR, China",
                "Honduras", "Croatia", "Haiti", "Hungary", "Indonesia", "Isle of Man",
                "India", "Ireland", "Iran, Islamic Rep.", "Iraq", "Iceland", "Israel",
                "Italy", "Jamaica", "Jordan", "Japan", "Kazakhstan", "Kenya", "Kyrgyz Republic",
                "Cambodia", "Kiribati", "St. Kitts and Nevis", "Korea, Rep.", "Kuwait",
                "Lao PDR", "Lebanon", "Liberia", "Libya", "St. Lucia", "Liechtenstein",
                "Sri Lanka", "Lesotho", "Lithuania", "Luxembourg", "Latvia", "Macao SAR, China",
                "St. Martin (French part)", "Morocco", "Monaco", "Moldova", "Madagascar",
                "Maldives", "Mexico", "Marshall Islands", "North Macedonia", "Mali",
                "Malta", "Myanmar", "Montenegro", "Mongolia", "Northern Mariana Islands",
                "Mozambique", "Mauritania", "Mauritius", "Malawi", "Malaysia", "Namibia",
                "New Caledonia", "Niger", "Nigeria", "Nicaragua", "Netherlands", "Norway",
                "Nepal", "Nauru", "New Zealand", "Oman", "Pakistan", "Panama", "Peru",
                "Philippines", "Palau", "Papua New Guinea", "Poland", "Puerto Rico",
                "Korea, Dem. People's Rep.", "Portugal", "Paraguay", "West Bank and Gaza",
                "French Polynesia", "Qatar", "Romania", "Russian Federation", "Rwanda",
                "Saudi Arabia", "Sudan", "Senegal", "Singapore", "Solomon Islands",
                "Sierra Leone", "El Salvador", "San Marino", "Somalia", "Serbia", "South Sudan",
                "Sao Tome and Principe", "Suriname", "Slovak Republic", "Slovenia",
                "Sweden", "Eswatini", "Sint Maarten (Dutch part)", "Seychelles",
                "Syrian Arab Republic", "Turks and Caicos Islands", "Chad", "Togo",
                "Thailand", "Tajikistan", "Turkmenistan", "Timor-Leste", "Tonga",
                "Trinidad and Tobago", "Tunisia", "Turkiye", "Tuvalu", "Tanzania", "Uganda",
                "Ukraine", "Uruguay", "United States", "Uzbekistan", "St. Vincent and the Grenadines",
                "Venezuela, RB", "British Virgin Islands", "Virgin Islands (U.S.)",
                "Viet Nam", "Vanuatu", "Samoa", "Kosovo", "Yemen, Rep.", "South Africa",
                "Zambia", "Zimbabwe"
        );
        Collections.sort(countries); // Sortiere die Länder alphabetisch
    }

    // Speichert die eingegebenen Emissionsdaten
    public String saveData() {
        try {
            double emissionsValue = Double.parseDouble(emissions.replace(",", ""));
            PendingEmission pendingEmission = new PendingEmission();
            pendingEmission.setCountry(country);
            pendingEmission.setYear(year);
            pendingEmission.setEmissions(emissionsValue);
            pendingEmission.setSubmittedBy(loginBean.getCurrentUser());

            pendingEmissionDAO.save(pendingEmission);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Daten erfolgreich gespeichert! Ihre Daten müssen noch genehmigt werden.", ""));

            // Setzt Emissionsfeld auf das formatierte Emissionsformat zurück
            emissions = formatNumber(emissionsValue);
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ungültiges Emissionsformat. Bitte geben Sie eine gültige Zahl ein.", ""));
        }

        return null; // Auf der aktuellen Seite bleiben
    }

    // Formatiert die Zahl
    private String formatNumber(double number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        return numberFormat.format(number);
    }

    // Getter und Setter
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEmissions() {
        return emissions;
    }

    public void setEmissions(String emissions) {
        this.emissions = emissions;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }
}
