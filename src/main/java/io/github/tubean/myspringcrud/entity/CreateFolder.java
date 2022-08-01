package io.github.tubean.myspringcrud.entity;

import javax.persistence.*;

@Entity
@Table(name = "FOLDER")
public class CreateFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FOLDER_NAME")
    private String folderName;

    public CreateFolder() {
    }

    public CreateFolder(Long id, String folderName) {
        this.id = id;
        this.folderName = folderName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
