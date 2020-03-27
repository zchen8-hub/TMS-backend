package CS542.group6.TMS.dto;

import CS542.group6.TMS.model.Tag;
import com.google.common.base.Converter;
import org.springframework.beans.BeanUtils;

public class TagDTO {

    private String tagId;
    private String tagName;
    private String projectId;

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Convert DTO to PO (Persistence object, completely match parameters of user table )
     *
     * @return PO
     */
    public Tag convertToTag() {
        TagDTOConvert tagDTOConvert = new TagDTOConvert();
        return tagDTOConvert.convert(this);
    }

    private static class TagDTOConvert extends Converter<TagDTO, Tag> {

        @Override
        protected Tag doForward(TagDTO tagDTO) {
            Tag tag = new Tag();
            BeanUtils.copyProperties(tagDTO, tag);
            return tag;
        }

        @Override
        protected TagDTO doBackward(Tag tag) {
            throw new AssertionError("Reversion is not supported");
        }
    }

}
