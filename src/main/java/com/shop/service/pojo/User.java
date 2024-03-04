package com.shop.service.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /** 用户id */
    private Long id;
    /** 用户账号 */
    private String account;
    /** 用户密码 */

    private String password;
    /** 用户昵称 */
    private String nickname;
    /** 生日 */
    private LocalDate birthday;
    /** 性别 */
    private String gender;
    /** 职业 */
    private String profession;
    /** 城市全称 */
    private String fullLocation;
    /** 头像 */
    private String avatar;
    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
