package com.daon.host.entity;

import lombok.*;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="tb_o_mobuser")
public class Users extends BaseTime implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//	@Id
//	@Column(name="user_idx")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idx;
	
	@Id
	@Column(name = "login_id")
    private String email;
	
	@Column(name = "login_pw")
	private String password;
	
	private String apt_id; 
	private String member_nm; 
	private String member_mobile; 
	private String blue_id; 
	private String dong; 
	private String ho; 
	private String household_yn; 
	private String car_no;
	private String car_type;
	
	@ColumnDefault("1")
	private int user_active;
	
	private String ins_id;
	private String upd_id;

    @Column
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> role = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.role.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}