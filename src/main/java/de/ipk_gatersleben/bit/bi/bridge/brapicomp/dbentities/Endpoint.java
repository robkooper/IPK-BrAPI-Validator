package de.ipk_gatersleben.bit.bi.bridge.brapicomp.dbentities;

import java.util.UUID;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Endpoint - table model
 * An endpoint instance contains the information related to one endpoint (url, and user it belongs to)
 * It represents the "endpoints" table.
 */
@DatabaseTable(tableName = "endpoints")
public class Endpoint {

    public static final String EMAIL_FIELD_NAME = "EMAIL";

    public static final String URL_FIELD_NAME = "URL";

    public static final String ID_FIELD_NAME = "ID";

    public static final String FREQUENCY_FIELD_NAME = "FREQUENCY";

    public static final String CONFIRMED_FIELD_NAME = "CONFIRMED";
    
    public static final String STOREPREV_FIELD_NAME = "STOREPREV";
    
    public static final String ISPUBLIC_FIELD_NAME = "ISPUBLIC";

    @DatabaseField(generatedId = true, columnName = ID_FIELD_NAME)
    private UUID id;

    @DatabaseField(canBeNull = false, columnName = URL_FIELD_NAME)
    private String url;

    @DatabaseField(columnName = EMAIL_FIELD_NAME)  //Null controlled in resource
    private String email;

    @DatabaseField(canBeNull = false, columnName = FREQUENCY_FIELD_NAME)
    private String frequency;

    @DatabaseField(canBeNull = false, columnName = CONFIRMED_FIELD_NAME)
    private boolean confirmed = false;

    @DatabaseField(canBeNull = false, columnName = STOREPREV_FIELD_NAME)
    private int storeprev = 3;
    
    @DatabaseField(canBeNull = false, columnName = ISPUBLIC_FIELD_NAME)
    private boolean isPublic = false;

	public Endpoint() {
    }

    public Endpoint(String url) {
        this.url = url;
    }

    public Endpoint(String u, String e, String f) throws IllegalArgumentException {
        this.setUrl(u);
        this.setEmail(e);
        this.setFrequency(f);
    }

    /**
     * @return endpoint's id
     */
    public UUID getId() {
        return id;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * @return endpoint's url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param e Endpoint's owner email.
     */
    public void setEmail(String e) {
        this.email = e;
    }

    /**
     * @return e Endpoint's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param f Endpoint's testing frequency.
     */
    public void setFrequency(String f) throws IllegalArgumentException {
        if (f.equals("weekly") || f.equals("monthly") || f.equals("daily")) {
            this.frequency = f;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public String getFrequency() {
        return frequency;
    }

    public void setConfirmed(boolean b) {
        this.confirmed = b;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public int getStoreprev() {
		return storeprev;
	}

	public void setStoreprev(int storeprev) {
        if (storeprev == 3) { // Only three is valid for now
            this.storeprev = storeprev;
        } else {
            throw new IllegalArgumentException();
        }
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	@Override
	public String toString() {
		return "Endpoint [id=" + id + ", url=" + url + ", email=" + email + ", frequency=" + frequency + ", confirmed="
				+ confirmed + ", storeprev=" + storeprev + ", isPublic=" + isPublic + "]";
	}
	
	
}
