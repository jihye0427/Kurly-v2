package kurly.domain.entity;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileBoardRepository extends JpaRepository<BoardEntity, Long>{

	List<BoardEntity> findAllByOrderByBoardIdxDesc();

	@Query("select file from BoardFileEntity file where board_idx=:boardIdx and idx=:idx")
	BoardFileEntity findBoardFileEntity(@Param("boardIdx")long boardIdx, @Param("idx")long idx);

	@Query("delete from BoardFileEntity file where board_idx=:boardIdx and idx=:idx")
	void deleteFileEntity(@Param("board_idx")long boardIdx,@Param("idx")long idx);


}
