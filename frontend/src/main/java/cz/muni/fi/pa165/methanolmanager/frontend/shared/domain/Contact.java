package cz.muni.fi.pa165.methanolmanager.frontend.shared.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Contact implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String email;
    private Date birthday;
    private Integer tutorialNote = Integer.valueOf(5); // Default Value
    private String subject = "About PWT"; // Default Value
    private String message;
}
