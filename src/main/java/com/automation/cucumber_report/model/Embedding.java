package com.automation.cucumber_report.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.codehaus.plexus.util.Base64;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;

@Entity
@SuperBuilder
@Setter
@Getter
@Table(name = "embedding")
public class Embedding implements EntityInterface{

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "embedding_id")
    private Long id;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "data")
    private String data;

    @Column(name = "name")
    private String name;

    @Column(name = "fileId")
    private String fileId;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "hook_entity_id")
    Hook hook;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "step_entity_id")
    private Steps steps;

    public static Embedding createEmbedding(String data,String fileId,String mimeType,String name){

        return Embedding.builder()
                .data(data)
                .fileId(fileId)
                .mimeType(mimeType)
                .name(name)
                .build();
    }

    public String getDecodedData() {
        return new String(Base64.decodeBase64(this.data.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
    }

    public String getExtension(){
        String var1 = this.mimeType;
        byte var2 = -1;
        switch(var1.hashCode()) {
            case -1487394660:
                if (var1.equals("image/jpeg")) {
                    var2 = 3;
                }
                break;
            case -1348221103:
                if (var1.equals("application/x-tar")) {
                    var2 = 15;
                }
                break;
            case -1248334925:
                if (var1.equals("application/pdf")) {
                    var2 = 14;
                }
                break;
            case -1248326952:
                if (var1.equals("application/xml")) {
                    var2 = 8;
                }
                break;
            case -1248325150:
                if (var1.equals("application/zip")) {
                    var2 = 9;
                }
                break;
            case -1082243251:
                if (var1.equals("text/html")) {
                    var2 = 4;
                }
                break;
            case -1004747228:
                if (var1.equals("text/csv")) {
                    var2 = 6;
                }
                break;
            case -1004727243:
                if (var1.equals("text/xml")) {
                    var2 = 5;
                }
                break;
            case -879272239:
                if (var1.equals("image/bmp")) {
                    var2 = 2;
                }
                break;
            case -879267568:
                if (var1.equals("image/gif")) {
                    var2 = 1;
                }
                break;
            case -879258763:
                if (var1.equals("image/png")) {
                    var2 = 0;
                }
                break;
            case -879253829:
                if (var1.equals("image/url")) {
                    var2 = 11;
                }
                break;
            case -227171396:
                if (var1.equals("image/svg+xml")) {
                    var2 = 12;
                }
                break;
            case -43923783:
                if (var1.equals("application/gzip")) {
                    var2 = 17;
                }
                break;
            case -43840953:
                if (var1.equals("application/json")) {
                    var2 = 7;
                }
                break;
            case 817335912:
                if (var1.equals("text/plain")) {
                    var2 = 13;
                }
                break;
            case 1331848029:
                if (var1.equals("video/mp4")) {
                    var2 = 10;
                }
                break;
            case 1423759679:
                if (var1.equals("application/x-bzip2")) {
                    var2 = 16;
                }
        }

        switch(var2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                return this.mimeType.substring(this.mimeType.indexOf(47) + 1);
            case 11:
                return "image";
            case 12:
                return "svg";
            case 13:
                return "txt";
            case 14:
                return "pdf";
            case 15:
                return "tar";
            case 16:
                return "bz2";
            case 17:
                return "gz";
            default:
                return "unknown";
        }
    }

}
