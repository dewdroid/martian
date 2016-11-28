package com.mokinjazz.and.martian.data.model;

import java.util.List;

/**
 * Created by vvasilyev on 12/29/15.
 */
public class Credits {

    public long id;

    public List<ActorAndCharacter> actorAndCharacter;

    public Credits(List<ActorAndCharacter> actorAndCharacter) {
        this.actorAndCharacter = actorAndCharacter;
    }

    public static Credits _(List<ActorAndCharacter> actorAndCharacter) {
        return new Credits(actorAndCharacter);
    }
}
