// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.ruyicai.draw.domain;

import com.ruyicai.draw.domain.UserDraw;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

privileged aspect UserDraw_Roo_Json {
    
    public String UserDraw.toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
    public static UserDraw UserDraw.fromJsonToUserDraw(String json) {
        return new JSONDeserializer<UserDraw>().use(null, UserDraw.class).deserialize(json);
    }
    
    public static String UserDraw.toJsonArray(Collection<UserDraw> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }
    
    public static Collection<UserDraw> UserDraw.fromJsonArrayToUserDraws(String json) {
        return new JSONDeserializer<List<UserDraw>>().use(null, ArrayList.class).use("values", UserDraw.class).deserialize(json);
    }
    
}
