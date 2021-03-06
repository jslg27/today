package com.mobila.project.today.model.dataProviding.dataAccess;

import com.mobila.project.today.model.Identifiable;

public interface AttachmentDataAccess extends ParentDataAccess {

    static AttachmentDataAccess getInstance() {
        return AttachmentDataAccessImpl.getInstance();
    }

    void setName(Identifiable attachment, String title) throws DataKeyNotFoundException;
}
