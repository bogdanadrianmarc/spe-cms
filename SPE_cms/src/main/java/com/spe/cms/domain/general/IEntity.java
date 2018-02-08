package com.spe.cms.domain.general;

public interface IEntity <ID> {

    /**
     * @return : the id of the object
     */
    ID getId();

    /**
     * @param id : the new id to be modified
     */
    void setId(ID id);

}
