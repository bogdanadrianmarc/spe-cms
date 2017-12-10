package com.spe.cms.domain;

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
