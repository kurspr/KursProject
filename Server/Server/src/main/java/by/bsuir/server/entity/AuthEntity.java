package by.bsuir.server.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthEntity {
    private String username;
    private String password;
    private String permission;
}
