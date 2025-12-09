package ua.kpi.iasa.onlineradio.models;

public class Administrator extends User {

    public Administrator(int id, String username, String passwordHash) {

        super(id, username, passwordHash);
    }

    public void manageStation(RadioStation station) {
        System.out.println("Адміністратор " + getUsername() + " керує станцією " + station.getName());

    }

    @Override
    public String toString() {
        return "Administrator{" +
               "id=" + getId() +
               ", username='" + getUsername() + '\'' +
               '}';
    }
}