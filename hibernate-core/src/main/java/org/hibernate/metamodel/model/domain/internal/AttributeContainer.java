/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or http://www.gnu.org/licenses/lgpl-2.1.html
 */
package org.hibernate.metamodel.model.domain.internal;

import java.util.List;
import java.util.Set;

import org.hibernate.metamodel.model.domain.PersistentAttribute;
import org.hibernate.metamodel.model.domain.SingularPersistentAttribute;

/**
 * @author Steve Ebersole
 */
public interface AttributeContainer<J> {
	InFlightAccess<J> getInFlightAccess();

	/**
	 * Used during creation of the type
	 */
	interface InFlightAccess<J> {
		void addAttribute(PersistentAttribute<J,?> attribute);

		/**
		 * Callback used when we have a singular id attribute of some form - either a simple id
		 * or an aggregated composite id ({@link javax.persistence.EmbeddedId})
		 */
		default void applyIdAttribute(SingularPersistentAttribute<J, ?> idAttribute) {
			throw new UnsupportedOperationException(
					"AttributeContainer [" + getClass().getName() + "] does not support identifiers"
			);
		}

		default void applyNonAggregatedIdAttributes(Set<SingularPersistentAttribute<? super J, ?>> idAttributes) {
			throw new UnsupportedOperationException(
					"AttributeContainer [" + getClass().getName() + "] does not support identifiers"
			);
		}

		/**
		 * todo (6.0) : we still need to implement this properly and the contract may change
		 * 		- specifically I am not certain we will be able to re-use `SingularPersistentAttribute`
		 * 		because of its dependence on declaring-type, etc that we may not be able to do
		 */
		default void applyIdClassAttributes(Set<SingularPersistentAttribute<? super J, ?>> idClassAttributes) {
			throw new UnsupportedOperationException(
					"AttributeContainer [" + getClass().getName() + "] does not support identifiers"
			);
		}

		default void applyVersionAttribute(SingularPersistentAttribute<J, ?> versionAttribute) {
			throw new UnsupportedOperationException(
					"AttributeContainer [" + getClass().getName() + "] does not support versions"
			);
		}


		/**
		 * Called when configuration of the type is complete
		 */
		void finishUp();
	}
}
