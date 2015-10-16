package URLShortener.Stats;


    import java.io.File;

    import com.maxmind.geoip.Location;
    import com.maxmind.geoip.LookupService;

public class GeoLocation {
    private static final GeoLocation istance = new GeoLocation();
    private File file;

    public static GeoLocation getIstance() {
        return istance;
    }

    private GeoLocation() {
        String path = System.getProperty("user.dir") + "\\src\\main\\java\\utilities";
        file = new File(path + "\\GeoLiteCity.dat");
    }

    public String getLocationCountry(String ipAddress) {
        ServerLocation serverLoc = null;
        try {

            serverLoc = new ServerLocation();
            LookupService lookup = new LookupService(file,
                LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(ipAddress);
            serverLoc.setCountryName(locationServices.countryName);

        } catch (Exception e) {
            serverLoc.setCountryName("Country not available");
        }
        return serverLoc.toStringCountry();

    }

    public String getLocationCity(String ipAddress) {
        ServerLocation serverLoc = null;
        try {

            serverLoc = new ServerLocation();
            LookupService lookup = new LookupService(file,
                LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(ipAddress);
            serverLoc.setCity(locationServices.city);

        } catch (Exception e) {
            serverLoc.setCity("City not available");
        }
        return serverLoc.toStringCity();

    }

    class ServerLocation {
        String country;
        String city;

        public void setCountryName(String country) {
            this.country = country;
        }

        public String toStringCountry() {
            return country;
        }

        public void setCity(String city) {
            this.city = city;

        }

        public String toStringCity() {
            return city;
        }
    }
}

