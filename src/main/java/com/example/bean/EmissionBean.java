package com.example.bean;

import com.example.dao.EmissionDAO;
import com.example.model.Emission;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Named
@RequestScoped
public class EmissionBean {
    @Inject
    private EmissionDAO emissionDAO;

    private String country;
    private List<Emission> emissions;
    private List<String> countries;

    public EmissionBean() {
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

    // Sucht die neuesten Emissionsdaten für das ausgewählte Land
    public void search() {
        emissions = emissionDAO.findLatestByCountry(country);
    }

    // Formatiert die Emissionsdaten für die Anzeige
    public String formatEmissions(double emissions) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        return decimalFormat.format(emissions);
    }

    // Getter und Setter
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Emission> getEmissions() {
        return emissions;
    }

    public List<String> getCountries() {
        return countries;
    }
}

