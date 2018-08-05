package com.ayouris.tawassol.common.model.bean;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.enums.MessageDestinationType;
import com.ayouris.tawassol.common.model.entity.Message;
import com.ayouris.tawassol.common.model.entity.Unite;
import com.ayouris.tawassol.common.model.enums.MessageStatus;
import com.ayouris.tawassol.common.model.enums.MessageType;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: m.wannas
 */
@Setter
@Getter
public final class ViewBean  {
    private UserBean userSeen;
    private LocalDateTime dateSeen;
    private List<ViewParent> parentsSeen;
    private UserBean userNotSeen;
    private List<ViewParent> parentsNotSeen;

    public void addParents(Boolean seen, ParentBean parent, Boolean parentHasSeen, LocalDateTime dateSeen) {
        if(seen) {
            if(parentsSeen == null) {
                parentsSeen = new ArrayList<ViewParent>();
            }
            ViewParent viewParent = new ViewParent(parent,parentHasSeen, dateSeen);

            parentsSeen.add(viewParent);
        } else {
            if(parentsNotSeen == null) {
                parentsNotSeen = new ArrayList<ViewParent>();
            }
            ViewParent viewParent = new ViewParent(parent,parentHasSeen, null);
            parentsNotSeen.add(viewParent);
        }
    }
}
@Setter
@Getter
class ViewParent {
    private ParentBean parent;
    private Boolean seen;
    private LocalDateTime dateSeen;

    ViewParent(ParentBean parent, Boolean seen, LocalDateTime dateSeen) {
        this.parent = parent;
        this.seen = seen;
        this.dateSeen = dateSeen;
    }
}