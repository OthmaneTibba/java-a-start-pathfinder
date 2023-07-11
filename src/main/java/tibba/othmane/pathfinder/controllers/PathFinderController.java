package tibba.othmane.pathfinder.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tibba.othmane.pathfinder.helpers.PathFinder;
import tibba.othmane.pathfinder.models.Node;
import tibba.othmane.pathfinder.models.NodeDto;
import tibba.othmane.pathfinder.models.RequestDto;

import java.util.List;

@RestController
@RequestMapping("/api/pathfinder")
@CrossOrigin
public class PathFinderController {


    @Autowired  PathFinder pathFinder;



    @PostMapping("/find")
    public ResponseEntity<List<NodeDto>> findPath(@RequestBody RequestDto request){
       try {
           pathFinder.setNodeDtos(request.getNodes());
           pathFinder.setNodes();
           if(pathFinder.search()){
               return ResponseEntity.ok(pathFinder.trackPath());
           }
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
       }catch (Exception e){
           throw new RuntimeException(e.getMessage());
       }

    }


}
