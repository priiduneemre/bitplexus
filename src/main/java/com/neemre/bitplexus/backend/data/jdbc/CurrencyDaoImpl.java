package com.neemre.bitplexus.backend.data.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.neemre.bitplexus.backend.data.CurrencyDao;
import com.neemre.bitplexus.backend.domain.Currency;

@Repository
public class CurrencyDaoImpl implements CurrencyDao {

	private static final String SQL_CURRENCY_CREATE = "INSERT INTO currency (name, abbreviation, " 
			+ "symbol) VALUES (?, ?, ?);";
	private static final String SQL_CURRENCY_READ = "SELECT * FROM currency WHERE currency_id = ?;";
	private static final String SQL_CURRENCY_READ_ALL = "SELECT * FROM currency;";
	private static final String SQL_CURRENCY_UPDATE = "UPDATE currency SET name = ?, "
			+ "abbreviation = ?, symbol = ? WHERE currency_id = ?;";
	private static final String SQL_CURRENCY_DELETE = "DELETE FROM currency WHERE currency_id = ?;";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public Integer create(final Currency currency) {
		KeyHolder idHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) 
					throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL_CURRENCY_CREATE, 
						new String[]{"currency_id"});
				ps.setString(1, currency.getName());
				ps.setString(2, currency.getAbbreviation());
				ps.setString(3, currency.getSymbol());
				return ps;
			}}, idHolder);
		return idHolder.getKey().intValue();
	}

	@Override
	public Currency read(Integer currencyId) {
		Currency currency = jdbcTemplate.queryForObject(SQL_CURRENCY_READ, new Object[]{currencyId},
				BeanPropertyRowMapper.newInstance(Currency.class));
		return currency;
	}

	@Override
	public List<Currency> readAll() {
		List<Currency> currencies = jdbcTemplate.query(SQL_CURRENCY_READ_ALL, new Object[] {}, 
				BeanPropertyRowMapper.newInstance(Currency.class));
		return currencies;
	}

	@Override
	public void update(Currency currency) {
		int rowsUpdated = jdbcTemplate.update(SQL_CURRENCY_UPDATE, new Object[]{currency.getName(),
				currency.getAbbreviation(), currency.getSymbol(), currency.getCurrencyId()});
		if(rowsUpdated != 1) {
			throw new IncorrectResultSizeDataAccessException("I am broken.", 1, rowsUpdated);		//TODO
		}
	}

	@Override
	public void delete(Integer currencyId) {
		int rowsDeleted = jdbcTemplate.update(SQL_CURRENCY_DELETE, currencyId);
		if(rowsDeleted != 1) {
			throw new IncorrectResultSizeDataAccessException("I am broken", 1, rowsDeleted);		//TODO
		}
	}
}