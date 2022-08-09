package io.github.tubean.myspringcrud.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "folder")
public class CreateFolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FOLDER_NAME")
    private String folderName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "hang")
    private String hang;

    public CreateFolder() {
    }

    public CreateFolder(Long id, String folderName, Date createDate, String hang) {
        this.id = id;
        this.folderName = folderName;
        this.createDate = createDate;
        this.hang = hang;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }
}
