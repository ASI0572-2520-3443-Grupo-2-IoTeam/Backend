package pe.iotteam.plantcare.community.interfaces.rest.transform;

import pe.iotteam.plantcare.community.domain.model.aggregates.CommunityMember;
import pe.iotteam.plantcare.community.interfaces.rest.resources.CommunityMemberResource;

public class CommunityMemberResourceAssembler {

    public static CommunityMemberResource toResource(CommunityMember member, String username) {
        return new CommunityMemberResource(
                member.getId(),
                username,
                member.getRole().name(),
                member.getJoinedAt());
    }
}