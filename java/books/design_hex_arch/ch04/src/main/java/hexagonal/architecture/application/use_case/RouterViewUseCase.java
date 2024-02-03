package hexagonal.architecture.application.use_case;

import hexagonal.architecture.domain.entity.Router;
import hexagonal.architecture.domain.vo.RouterType;

import java.util.List;

public interface RouterViewUseCase {

    List<Router> getRelatedRouters(
        RelatedRoutersCommand relatedRoutersCommand
    );

    class RelatedRoutersCommand {

        private RouterType type;

        public RelatedRoutersCommand(String type) {
            this.type = RouterType.valueOf(type);
        }

        public RouterType getType() {
            return type;
        }

    }

}
