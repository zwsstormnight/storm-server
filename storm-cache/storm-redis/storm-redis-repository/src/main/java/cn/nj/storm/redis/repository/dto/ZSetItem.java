package cn.nj.storm.redis.repository.dto;

import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/4/10]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ZSetItem implements Serializable
{
    private static final long serialVersionUID = 1L;

    String member;

    Double score;

    /**
     * @return 返回 member
     */
    public String getMember()
    {
        return member;
    }

    /**
     * @param member
     */
    public void setMember(String member)
    {
        this.member = member;
    }

    /**
     * @return 返回 score
     */
    public Double getScore()
    {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(Double score)
    {
        this.score = score;
    }

}
