package nextstep.subway.maps.map.ui;

import nextstep.subway.auth.application.UserDetails;
import nextstep.subway.auth.domain.AuthenticationPrincipal;
import nextstep.subway.maps.map.application.MapService;
import nextstep.subway.maps.map.domain.PathType;
import nextstep.subway.maps.map.dto.MapResponse;
import nextstep.subway.maps.map.dto.PathResponse;
import nextstep.subway.members.member.domain.LoginMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MapController {
    private MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("/paths")
    public ResponseEntity<PathResponse> findPath(@RequestParam Long source, @RequestParam Long target,
                                                 @RequestParam PathType type, @AuthenticationPrincipal UserDetails userDetails) {
        final Long memberId = extractMemberIdFromUserDetails(userDetails);
        return ResponseEntity.ok(mapService.findPath(source, target, type, memberId));
    }

    @GetMapping("/maps")
    public ResponseEntity<MapResponse> findMap() {
        MapResponse response = mapService.findMap();
        return ResponseEntity.ok(response);
    }

    private Long extractMemberIdFromUserDetails(UserDetails userDetails) {
        if (userDetails instanceof LoginMember) {
            final LoginMember loginMember = (LoginMember) userDetails;
            return loginMember.getId();
        }
        return null;
    }
}
