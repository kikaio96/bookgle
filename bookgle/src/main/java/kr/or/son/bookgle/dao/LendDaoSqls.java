package kr.or.son.bookgle.dao;

public class LendDaoSqls {
	public static final String SELECT_PAGING = "select lend.book_id, user.user_id, lend_id, lend_date, return_date,"
											+ " book.name book_name, user.name user_name"
											+ " from lend left join user on lend.user_id = user.user_id"
											+ " left join book on lend.book_id = book.book_id"
											+ " order by lend_id limit :start, :limit";
	
	public static final String SELECT_COUNT = "select count(*) from lend";
	
	public static final String SELECT_PAGING_BY_USER_ID = "select lend.book_id, user.user_id, lend_id, lend_date, return_date,"
														+ " book.name book_name, user.name user_name"
														+ " from lend left join user on lend.user_id = user.user_id"
														+ " left join book on lend.book_id = book.book_id"
														+ " where lend.user_id = :user_id"
														+ " order by lend_id limit :start, :limit";

	public static final String SELECT_COUNT_BY_USER_ID = "select count(*) from lend where user_id = :user_id";
	
	public static final String DELETE_BY_ID = "delete from lend where lend_id = :lend_id";
	
	public static final String UPDATE_BY_ID = "update lend set"
											+ " return_date = :return_date"
											+ " where lend_id = :lend_id";
}
