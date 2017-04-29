package model.impl;

import java.util.Collection;
import java.util.Set;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.StringBridge;

public class SetStringFieldBridge implements FieldBridge, StringBridge {

	@Override
	public String objectToString(Object object) {

		if(object == null){
			
			return null;
		}
		
		else if( !(object instanceof String)){
			
			throw new IllegalArgumentException( "This FieldBridge only supports String objects." );
		}
		return object.toString();
	}

	@Override
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {

		
		if(!(value instanceof Set)){
			
			throw new IllegalArgumentException("Hata gelen veri Set türünde degil");
		}
		
		Collection<?> collection = (Collection<?>) value;
		
		for (Object object : collection) {
			
			luceneOptions.addFieldToDocument(name, objectToString(object), document);
		}
		
	}

}
