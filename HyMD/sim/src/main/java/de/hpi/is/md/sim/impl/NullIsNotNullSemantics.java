package de.hpi.is.md.sim.impl;

import com.bakdata.util.jackson.CPSType;
import de.hpi.is.md.sim.SimilarityMeasure;
import de.hpi.is.md.util.Hasher;
import de.hpi.is.md.util.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@CPSType(id = "null_is_not_null", base = SimilarityMeasure.class)
@RequiredArgsConstructor
public class NullIsNotNullSemantics<T> implements SimilarityMeasure<T> {

	private static final long serialVersionUID = -2179939183451948005L;
	private final SimilarityMeasure<T> underlying;

	@Override
	public double calculateSimilarity(T obj1, T obj2) {
		if (ObjectUtils.eitherNull(obj1, obj2)) {
			return SimilarityMeasure.MIN_SIMILARITY;
		}
		return underlying.calculateSimilarity(obj1, obj2);
	}

	@Override
	public void hash(Hasher hasher) {
		Class<?> clazz = this.getClass();
		hasher
			.putClass(clazz)
			.put(underlying);
	}
}
