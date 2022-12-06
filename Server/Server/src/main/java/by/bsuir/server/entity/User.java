package by.bsuir.server.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {
    private String id;
    private String fio;
    private String email;
    private String phone;
    private String address;
    private String fpassword;
}
