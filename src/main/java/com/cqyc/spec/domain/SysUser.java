package com.cqyc.spec.domain;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.io.Serializable;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author cqyc
* @since 2019-07-05
*/
    @Data
        @EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private String loginName;

    private String passwrod;

    private String userType;

            /**
            * 用户是否删除
            */
    private String delFlag;

            /**
            * 已经投票的组
            */
    private String votingGroup;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
