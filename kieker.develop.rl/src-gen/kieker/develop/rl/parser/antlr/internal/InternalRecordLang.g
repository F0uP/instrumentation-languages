/*
 * generated by Xtext
 */
grammar InternalRecordLang;

options {
	superClass=AbstractInternalAntlrParser;
	
}

@lexer::header {
package kieker.develop.rl.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

@parser::header {
package kieker.develop.rl.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import kieker.develop.rl.services.RecordLangGrammarAccess;

}

@parser::members {

 	private RecordLangGrammarAccess grammarAccess;
 	
    public InternalRecordLangParser(TokenStream input, RecordLangGrammarAccess grammarAccess) {
        this(input);
        this.grammarAccess = grammarAccess;
        registerRules(grammarAccess.getGrammar());
    }
    
    @Override
    protected String getFirstRuleName() {
    	return "Model";	
   	}
   	
   	@Override
   	protected RecordLangGrammarAccess getGrammarAccess() {
   		return grammarAccess;
   	}
}

@rulecatch { 
    catch (RecognitionException re) { 
        recover(input,re); 
        appendSkippedTokens();
    } 
}




// Entry rule entryRuleModel
entryRuleModel returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getModelRule()); }
	 iv_ruleModel=ruleModel 
	 { $current=$iv_ruleModel.current; } 
	 EOF 
;

// Rule Model
ruleModel returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='package' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getModelAccess().getPackageKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getModelAccess().getNameQualifiedNameParserRuleCall_1_0()); 
	    }
		lv_name_1_0=ruleQualifiedName		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModelRule());
	        }
       		set(
       			$current, 
       			"name",
        		lv_name_1_0, 
        		"kieker.develop.rl.RecordLang.QualifiedName");
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getModelAccess().getImportsImportParserRuleCall_2_0()); 
	    }
		lv_imports_2_0=ruleImport		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModelRule());
	        }
       		add(
       			$current, 
       			"imports",
        		lv_imports_2_0, 
        		"kieker.develop.rl.RecordLang.Import");
	        afterParserOrEnumRuleCall();
	    }

)
)*(
(
		{ 
	        newCompositeNode(grammarAccess.getModelAccess().getTypesComplexTypeParserRuleCall_3_0()); 
	    }
		lv_types_3_0=ruleComplexType		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getModelRule());
	        }
       		add(
       			$current, 
       			"types",
        		lv_types_3_0, 
        		"kieker.develop.rl.RecordLang.ComplexType");
	        afterParserOrEnumRuleCall();
	    }

)
)*)
;





// Entry rule entryRuleImport
entryRuleImport returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getImportRule()); }
	 iv_ruleImport=ruleImport 
	 { $current=$iv_ruleImport.current; } 
	 EOF 
;

// Rule Import
ruleImport returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='import' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_1_0()); 
	    }
		lv_importedNamespace_1_0=ruleQualifiedNameWithWildcard		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getImportRule());
	        }
       		set(
       			$current, 
       			"importedNamespace",
        		lv_importedNamespace_1_0, 
        		"kieker.develop.rl.RecordLang.QualifiedNameWithWildcard");
	        afterParserOrEnumRuleCall();
	    }

)
))
;







// Entry rule entryRuleBaseType
entryRuleBaseType returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getBaseTypeRule()); }
	 iv_ruleBaseType=ruleBaseType 
	 { $current=$iv_ruleBaseType.current; } 
	 EOF 
;

// Rule BaseType
ruleBaseType returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_name_0_0=RULE_ID
		{
			newLeafNode(lv_name_0_0, grammarAccess.getBaseTypeAccess().getNameIDTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getBaseTypeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_0_0, 
        		"org.eclipse.xtext.common.Terminals.ID");
	    }

)
)
;





// Entry rule entryRuleComplexType
entryRuleComplexType returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getComplexTypeRule()); }
	 iv_ruleComplexType=ruleComplexType 
	 { $current=$iv_ruleComplexType.current; } 
	 EOF 
;

// Rule ComplexType
ruleComplexType returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getComplexTypeAccess().getRecordTypeParserRuleCall_0()); 
    }
    this_RecordType_0=ruleRecordType
    { 
        $current = $this_RecordType_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getComplexTypeAccess().getTemplateTypeParserRuleCall_1()); 
    }
    this_TemplateType_1=ruleTemplateType
    { 
        $current = $this_TemplateType_1.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleTemplateType
entryRuleTemplateType returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getTemplateTypeRule()); }
	 iv_ruleTemplateType=ruleTemplateType 
	 { $current=$iv_ruleTemplateType.current; } 
	 EOF 
;

// Rule TemplateType
ruleTemplateType returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((	otherlv_0='@author' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getTemplateTypeAccess().getAuthorKeyword_0_0());
    }
(
(
		lv_author_1_0=RULE_STRING
		{
			newLeafNode(lv_author_1_0, grammarAccess.getTemplateTypeAccess().getAuthorSTRINGTerminalRuleCall_0_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTemplateTypeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"author",
        		lv_author_1_0, 
        		"org.eclipse.xtext.common.Terminals.STRING");
	    }

)
))?(	otherlv_2='@since' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getTemplateTypeAccess().getSinceKeyword_1_0());
    }
(
(
		lv_since_3_0=RULE_STRING
		{
			newLeafNode(lv_since_3_0, grammarAccess.getTemplateTypeAccess().getSinceSTRINGTerminalRuleCall_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTemplateTypeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"since",
        		lv_since_3_0, 
        		"org.eclipse.xtext.common.Terminals.STRING");
	    }

)
))?	otherlv_4='template' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getTemplateTypeAccess().getTemplateKeyword_2());
    }
(
(
		lv_name_5_0=RULE_ID
		{
			newLeafNode(lv_name_5_0, grammarAccess.getTemplateTypeAccess().getNameIDTerminalRuleCall_3_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getTemplateTypeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_5_0, 
        		"org.eclipse.xtext.common.Terminals.ID");
	    }

)
)(	otherlv_6=':' 
    {
    	newLeafNode(otherlv_6, grammarAccess.getTemplateTypeAccess().getColonKeyword_4_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getTemplateTypeRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getTemplateTypeAccess().getParentsTemplateTypeCrossReference_4_1_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_8=',' 
    {
    	newLeafNode(otherlv_8, grammarAccess.getTemplateTypeAccess().getCommaKeyword_4_2_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getTemplateTypeRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getTemplateTypeAccess().getParentsTemplateTypeCrossReference_4_2_1_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
))*)?(	otherlv_10='{' 
    {
    	newLeafNode(otherlv_10, grammarAccess.getTemplateTypeAccess().getLeftCurlyBracketKeyword_5_0());
    }
((
(
		{ 
	        newCompositeNode(grammarAccess.getTemplateTypeAccess().getPropertiesPropertyParserRuleCall_5_1_0_0()); 
	    }
		lv_properties_11_0=ruleProperty		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getTemplateTypeRule());
	        }
       		add(
       			$current, 
       			"properties",
        		lv_properties_11_0, 
        		"kieker.develop.rl.RecordLang.Property");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |(
(
		{ 
	        newCompositeNode(grammarAccess.getTemplateTypeAccess().getConstantsConstantParserRuleCall_5_1_1_0()); 
	    }
		lv_constants_12_0=ruleConstant		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getTemplateTypeRule());
	        }
       		add(
       			$current, 
       			"constants",
        		lv_constants_12_0, 
        		"kieker.develop.rl.RecordLang.Constant");
	        afterParserOrEnumRuleCall();
	    }

)
))*	otherlv_13='}' 
    {
    	newLeafNode(otherlv_13, grammarAccess.getTemplateTypeAccess().getRightCurlyBracketKeyword_5_2());
    }
)?)
;





// Entry rule entryRuleRecordType
entryRuleRecordType returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getRecordTypeRule()); }
	 iv_ruleRecordType=ruleRecordType 
	 { $current=$iv_ruleRecordType.current; } 
	 EOF 
;

// Rule RecordType
ruleRecordType returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((	otherlv_0='@author' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getRecordTypeAccess().getAuthorKeyword_0_0());
    }
(
(
		lv_author_1_0=RULE_STRING
		{
			newLeafNode(lv_author_1_0, grammarAccess.getRecordTypeAccess().getAuthorSTRINGTerminalRuleCall_0_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getRecordTypeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"author",
        		lv_author_1_0, 
        		"org.eclipse.xtext.common.Terminals.STRING");
	    }

)
))?(	otherlv_2='@since' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getRecordTypeAccess().getSinceKeyword_1_0());
    }
(
(
		lv_since_3_0=RULE_STRING
		{
			newLeafNode(lv_since_3_0, grammarAccess.getRecordTypeAccess().getSinceSTRINGTerminalRuleCall_1_1_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getRecordTypeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"since",
        		lv_since_3_0, 
        		"org.eclipse.xtext.common.Terminals.STRING");
	    }

)
))?(
(
		lv_abstract_4_0=	'abstract' 
    {
        newLeafNode(lv_abstract_4_0, grammarAccess.getRecordTypeAccess().getAbstractAbstractKeyword_2_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getRecordTypeRule());
	        }
       		setWithLastConsumed($current, "abstract", true, "abstract");
	    }

)
)?	otherlv_5='entity' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getRecordTypeAccess().getEntityKeyword_3());
    }
(
(
		lv_name_6_0=RULE_ID
		{
			newLeafNode(lv_name_6_0, grammarAccess.getRecordTypeAccess().getNameIDTerminalRuleCall_4_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getRecordTypeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_6_0, 
        		"org.eclipse.xtext.common.Terminals.ID");
	    }

)
)(	otherlv_7='extends' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getRecordTypeAccess().getExtendsKeyword_5_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getRecordTypeRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getRecordTypeAccess().getParentRecordTypeCrossReference_5_1_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
))?(	otherlv_9=':' 
    {
    	newLeafNode(otherlv_9, grammarAccess.getRecordTypeAccess().getColonKeyword_6_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getRecordTypeRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getRecordTypeAccess().getParentsTemplateTypeCrossReference_6_1_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_11=',' 
    {
    	newLeafNode(otherlv_11, grammarAccess.getRecordTypeAccess().getCommaKeyword_6_2_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getRecordTypeRule());
	        }
        }
		{ 
	        newCompositeNode(grammarAccess.getRecordTypeAccess().getParentsTemplateTypeCrossReference_6_2_1_0()); 
	    }
		ruleQualifiedName		{ 
	        afterParserOrEnumRuleCall();
	    }

)
))*)?(	otherlv_13='{' 
    {
    	newLeafNode(otherlv_13, grammarAccess.getRecordTypeAccess().getLeftCurlyBracketKeyword_7_0());
    }
((
(
		{ 
	        newCompositeNode(grammarAccess.getRecordTypeAccess().getPropertiesPropertyParserRuleCall_7_1_0_0()); 
	    }
		lv_properties_14_0=ruleProperty		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getRecordTypeRule());
	        }
       		add(
       			$current, 
       			"properties",
        		lv_properties_14_0, 
        		"kieker.develop.rl.RecordLang.Property");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |(
(
		{ 
	        newCompositeNode(grammarAccess.getRecordTypeAccess().getConstantsConstantParserRuleCall_7_1_1_0()); 
	    }
		lv_constants_15_0=ruleConstant		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getRecordTypeRule());
	        }
       		add(
       			$current, 
       			"constants",
        		lv_constants_15_0, 
        		"kieker.develop.rl.RecordLang.Constant");
	        afterParserOrEnumRuleCall();
	    }

)
))*	otherlv_16='}' 
    {
    	newLeafNode(otherlv_16, grammarAccess.getRecordTypeAccess().getRightCurlyBracketKeyword_7_2());
    }
)?)
;





// Entry rule entryRuleConstant
entryRuleConstant returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getConstantRule()); }
	 iv_ruleConstant=ruleConstant 
	 { $current=$iv_ruleConstant.current; } 
	 EOF 
;

// Rule Constant
ruleConstant returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='const' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getConstantAccess().getConstKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getConstantAccess().getTypeClassifierParserRuleCall_1_0()); 
	    }
		lv_type_1_0=ruleClassifier		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getConstantRule());
	        }
       		set(
       			$current, 
       			"type",
        		lv_type_1_0, 
        		"kieker.develop.rl.RecordLang.Classifier");
	        afterParserOrEnumRuleCall();
	    }

)
)(
(
		lv_name_2_0=RULE_ID
		{
			newLeafNode(lv_name_2_0, grammarAccess.getConstantAccess().getNameIDTerminalRuleCall_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getConstantRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_2_0, 
        		"org.eclipse.xtext.common.Terminals.ID");
	    }

)
)	otherlv_3='=' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getConstantAccess().getEqualsSignKeyword_3());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getConstantAccess().getValueLiteralParserRuleCall_4_0()); 
	    }
		lv_value_4_0=ruleLiteral		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getConstantRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_4_0, 
        		"kieker.develop.rl.RecordLang.Literal");
	        afterParserOrEnumRuleCall();
	    }

)
))
;





// Entry rule entryRuleProperty
entryRuleProperty returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getPropertyRule()); }
	 iv_ruleProperty=ruleProperty 
	 { $current=$iv_ruleProperty.current; } 
	 EOF 
;

// Rule Property
ruleProperty returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{ 
	        newCompositeNode(grammarAccess.getPropertyAccess().getModifiersPropertyModifierEnumRuleCall_0_0()); 
	    }
		lv_modifiers_0_0=rulePropertyModifier		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPropertyRule());
	        }
       		add(
       			$current, 
       			"modifiers",
        		lv_modifiers_0_0, 
        		"kieker.develop.rl.RecordLang.PropertyModifier");
	        afterParserOrEnumRuleCall();
	    }

)
)*(
(
		{ 
	        newCompositeNode(grammarAccess.getPropertyAccess().getForeignKeyForeignKeyParserRuleCall_1_0()); 
	    }
		lv_foreignKey_1_0=ruleForeignKey		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPropertyRule());
	        }
       		set(
       			$current, 
       			"foreignKey",
        		lv_foreignKey_1_0, 
        		"kieker.develop.rl.RecordLang.ForeignKey");
	        afterParserOrEnumRuleCall();
	    }

)
)?((
(
		{ 
	        newCompositeNode(grammarAccess.getPropertyAccess().getTypeClassifierParserRuleCall_2_0_0()); 
	    }
		lv_type_2_0=ruleClassifier		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPropertyRule());
	        }
       		set(
       			$current, 
       			"type",
        		lv_type_2_0, 
        		"kieker.develop.rl.RecordLang.Classifier");
	        afterParserOrEnumRuleCall();
	    }

)
)
    |(	otherlv_3='alias' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getPropertyAccess().getAliasKeyword_2_1_0());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getPropertyRule());
	        }
        }
	otherlv_4=RULE_ID
	{
		newLeafNode(otherlv_4, grammarAccess.getPropertyAccess().getReferToPropertyCrossReference_2_1_1_0()); 
	}

)
)	otherlv_5='as' 
    {
    	newLeafNode(otherlv_5, grammarAccess.getPropertyAccess().getAsKeyword_2_1_2());
    }
))(
(
		lv_name_6_0=RULE_ID
		{
			newLeafNode(lv_name_6_0, grammarAccess.getPropertyAccess().getNameIDTerminalRuleCall_3_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getPropertyRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"name",
        		lv_name_6_0, 
        		"org.eclipse.xtext.common.Terminals.ID");
	    }

)
)(	otherlv_7='=' 
    {
    	newLeafNode(otherlv_7, grammarAccess.getPropertyAccess().getEqualsSignKeyword_4_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getPropertyAccess().getValueLiteralParserRuleCall_4_1_0()); 
	    }
		lv_value_8_0=ruleLiteral		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getPropertyRule());
	        }
       		set(
       			$current, 
       			"value",
        		lv_value_8_0, 
        		"kieker.develop.rl.RecordLang.Literal");
	        afterParserOrEnumRuleCall();
	    }

)
))?)
;





// Entry rule entryRuleForeignKey
entryRuleForeignKey returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getForeignKeyRule()); }
	 iv_ruleForeignKey=ruleForeignKey 
	 { $current=$iv_ruleForeignKey.current; } 
	 EOF 
;

// Rule ForeignKey
ruleForeignKey returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='grouped' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getForeignKeyAccess().getGroupedKeyword_0());
    }
	otherlv_1='by' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getForeignKeyAccess().getByKeyword_1());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getForeignKeyRule());
	        }
        }
	otherlv_2=RULE_ID
	{
		newLeafNode(otherlv_2, grammarAccess.getForeignKeyAccess().getRecordTypeRecordTypeCrossReference_2_0()); 
	}

)
)	otherlv_3='.' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getForeignKeyAccess().getFullStopKeyword_3());
    }
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getForeignKeyRule());
	        }
        }
	otherlv_4=RULE_ID
	{
		newLeafNode(otherlv_4, grammarAccess.getForeignKeyAccess().getPropertyRefPropertyCrossReference_4_0()); 
	}

)
))
;





// Entry rule entryRuleClassifier
entryRuleClassifier returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getClassifierRule()); }
	 iv_ruleClassifier=ruleClassifier 
	 { $current=$iv_ruleClassifier.current; } 
	 EOF 
;

// Rule Classifier
ruleClassifier returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getClassifierRule());
	        }
        }
	otherlv_0=RULE_ID
	{
		newLeafNode(otherlv_0, grammarAccess.getClassifierAccess().getTypeBaseTypeCrossReference_0_0()); 
	}

)
)(
(
		{ 
	        newCompositeNode(grammarAccess.getClassifierAccess().getSizesArraySizeParserRuleCall_1_0()); 
	    }
		lv_sizes_1_0=ruleArraySize		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getClassifierRule());
	        }
       		add(
       			$current, 
       			"sizes",
        		lv_sizes_1_0, 
        		"kieker.develop.rl.RecordLang.ArraySize");
	        afterParserOrEnumRuleCall();
	    }

)
)*)
;





// Entry rule entryRuleArraySize
entryRuleArraySize returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getArraySizeRule()); }
	 iv_ruleArraySize=ruleArraySize 
	 { $current=$iv_ruleArraySize.current; } 
	 EOF 
;

// Rule ArraySize
ruleArraySize returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getArraySizeAccess().getArraySizeAction_0(),
            $current);
    }
)	otherlv_1='[' 
    {
    	newLeafNode(otherlv_1, grammarAccess.getArraySizeAccess().getLeftSquareBracketKeyword_1());
    }
(
(
		lv_size_2_0=RULE_INT
		{
			newLeafNode(lv_size_2_0, grammarAccess.getArraySizeAccess().getSizeINTTerminalRuleCall_2_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getArraySizeRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"size",
        		lv_size_2_0, 
        		"kieker.develop.rl.RecordLang.INT");
	    }

)
)?	otherlv_3=']' 
    {
    	newLeafNode(otherlv_3, grammarAccess.getArraySizeAccess().getRightSquareBracketKeyword_3());
    }
)
;





// Entry rule entryRuleLiteral
entryRuleLiteral returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getLiteralRule()); }
	 iv_ruleLiteral=ruleLiteral 
	 { $current=$iv_ruleLiteral.current; } 
	 EOF 
;

// Rule Literal
ruleLiteral returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getLiteralAccess().getStringLiteralParserRuleCall_0()); 
    }
    this_StringLiteral_0=ruleStringLiteral
    { 
        $current = $this_StringLiteral_0.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getLiteralAccess().getIntLiteralParserRuleCall_1()); 
    }
    this_IntLiteral_1=ruleIntLiteral
    { 
        $current = $this_IntLiteral_1.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getLiteralAccess().getFloatLiteralParserRuleCall_2()); 
    }
    this_FloatLiteral_2=ruleFloatLiteral
    { 
        $current = $this_FloatLiteral_2.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getLiteralAccess().getBooleanLiteralParserRuleCall_3()); 
    }
    this_BooleanLiteral_3=ruleBooleanLiteral
    { 
        $current = $this_BooleanLiteral_3.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getLiteralAccess().getConstantLiteralParserRuleCall_4()); 
    }
    this_ConstantLiteral_4=ruleConstantLiteral
    { 
        $current = $this_ConstantLiteral_4.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getLiteralAccess().getArrayLiteralParserRuleCall_5()); 
    }
    this_ArrayLiteral_5=ruleArrayLiteral
    { 
        $current = $this_ArrayLiteral_5.current; 
        afterParserOrEnumRuleCall();
    }

    |
    { 
        newCompositeNode(grammarAccess.getLiteralAccess().getBuiltInValueLiteralParserRuleCall_6()); 
    }
    this_BuiltInValueLiteral_6=ruleBuiltInValueLiteral
    { 
        $current = $this_BuiltInValueLiteral_6.current; 
        afterParserOrEnumRuleCall();
    }
)
;





// Entry rule entryRuleArrayLiteral
entryRuleArrayLiteral returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getArrayLiteralRule()); }
	 iv_ruleArrayLiteral=ruleArrayLiteral 
	 { $current=$iv_ruleArrayLiteral.current; } 
	 EOF 
;

// Rule ArrayLiteral
ruleArrayLiteral returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(	otherlv_0='{' 
    {
    	newLeafNode(otherlv_0, grammarAccess.getArrayLiteralAccess().getLeftCurlyBracketKeyword_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getArrayLiteralAccess().getLiteralsLiteralParserRuleCall_1_0()); 
	    }
		lv_literals_1_0=ruleLiteral		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getArrayLiteralRule());
	        }
       		add(
       			$current, 
       			"literals",
        		lv_literals_1_0, 
        		"kieker.develop.rl.RecordLang.Literal");
	        afterParserOrEnumRuleCall();
	    }

)
)(	otherlv_2=',' 
    {
    	newLeafNode(otherlv_2, grammarAccess.getArrayLiteralAccess().getCommaKeyword_2_0());
    }
(
(
		{ 
	        newCompositeNode(grammarAccess.getArrayLiteralAccess().getLiteralsLiteralParserRuleCall_2_1_0()); 
	    }
		lv_literals_3_0=ruleLiteral		{
	        if ($current==null) {
	            $current = createModelElementForParent(grammarAccess.getArrayLiteralRule());
	        }
       		add(
       			$current, 
       			"literals",
        		lv_literals_3_0, 
        		"kieker.develop.rl.RecordLang.Literal");
	        afterParserOrEnumRuleCall();
	    }

)
))*	otherlv_4='}' 
    {
    	newLeafNode(otherlv_4, grammarAccess.getArrayLiteralAccess().getRightCurlyBracketKeyword_3());
    }
)
;





// Entry rule entryRuleStringLiteral
entryRuleStringLiteral returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getStringLiteralRule()); }
	 iv_ruleStringLiteral=ruleStringLiteral 
	 { $current=$iv_ruleStringLiteral.current; } 
	 EOF 
;

// Rule StringLiteral
ruleStringLiteral returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_value_0_0=RULE_STRING
		{
			newLeafNode(lv_value_0_0, grammarAccess.getStringLiteralAccess().getValueSTRINGTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getStringLiteralRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"org.eclipse.xtext.common.Terminals.STRING");
	    }

)
)
;





// Entry rule entryRuleIntLiteral
entryRuleIntLiteral returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getIntLiteralRule()); }
	 iv_ruleIntLiteral=ruleIntLiteral 
	 { $current=$iv_ruleIntLiteral.current; } 
	 EOF 
;

// Rule IntLiteral
ruleIntLiteral returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_value_0_0=RULE_INT
		{
			newLeafNode(lv_value_0_0, grammarAccess.getIntLiteralAccess().getValueINTTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getIntLiteralRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"kieker.develop.rl.RecordLang.INT");
	    }

)
)
;





// Entry rule entryRuleFloatLiteral
entryRuleFloatLiteral returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getFloatLiteralRule()); }
	 iv_ruleFloatLiteral=ruleFloatLiteral 
	 { $current=$iv_ruleFloatLiteral.current; } 
	 EOF 
;

// Rule FloatLiteral
ruleFloatLiteral returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_value_0_0=RULE_FLOAT
		{
			newLeafNode(lv_value_0_0, grammarAccess.getFloatLiteralAccess().getValueFLOATTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getFloatLiteralRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"kieker.develop.rl.RecordLang.FLOAT");
	    }

)
)
;





// Entry rule entryRuleBooleanLiteral
entryRuleBooleanLiteral returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getBooleanLiteralRule()); }
	 iv_ruleBooleanLiteral=ruleBooleanLiteral 
	 { $current=$iv_ruleBooleanLiteral.current; } 
	 EOF 
;

// Rule BooleanLiteral
ruleBooleanLiteral returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		lv_value_0_0=RULE_BOOLEAN
		{
			newLeafNode(lv_value_0_0, grammarAccess.getBooleanLiteralAccess().getValueBOOLEANTerminalRuleCall_0()); 
		}
		{
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getBooleanLiteralRule());
	        }
       		setWithLastConsumed(
       			$current, 
       			"value",
        		lv_value_0_0, 
        		"kieker.develop.rl.RecordLang.BOOLEAN");
	    }

)
)
;





// Entry rule entryRuleConstantLiteral
entryRuleConstantLiteral returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getConstantLiteralRule()); }
	 iv_ruleConstantLiteral=ruleConstantLiteral 
	 { $current=$iv_ruleConstantLiteral.current; } 
	 EOF 
;

// Rule ConstantLiteral
ruleConstantLiteral returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
(
		{
			if ($current==null) {
	            $current = createModelElement(grammarAccess.getConstantLiteralRule());
	        }
        }
	otherlv_0=RULE_ID
	{
		newLeafNode(otherlv_0, grammarAccess.getConstantLiteralAccess().getValueConstantCrossReference_0()); 
	}

)
)
;





// Entry rule entryRuleBuiltInValueLiteral
entryRuleBuiltInValueLiteral returns [EObject current=null] 
	:
	{ newCompositeNode(grammarAccess.getBuiltInValueLiteralRule()); }
	 iv_ruleBuiltInValueLiteral=ruleBuiltInValueLiteral 
	 { $current=$iv_ruleBuiltInValueLiteral.current; } 
	 EOF 
;

// Rule BuiltInValueLiteral
ruleBuiltInValueLiteral returns [EObject current=null] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
((
    {
        $current = forceCreateModelElement(
            grammarAccess.getBuiltInValueLiteralAccess().getBuiltInValueLiteralAction_0(),
            $current);
    }
)(
(
		lv_value_1_0=	'KIEKER_VERSION' 
    {
        newLeafNode(lv_value_1_0, grammarAccess.getBuiltInValueLiteralAccess().getValueKIEKER_VERSIONKeyword_1_0());
    }
 
	    {
	        if ($current==null) {
	            $current = createModelElement(grammarAccess.getBuiltInValueLiteralRule());
	        }
       		setWithLastConsumed($current, "value", lv_value_1_0, "KIEKER_VERSION");
	    }

)
))
;





// Entry rule entryRuleQualifiedName
entryRuleQualifiedName returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getQualifiedNameRule()); } 
	 iv_ruleQualifiedName=ruleQualifiedName 
	 { $current=$iv_ruleQualifiedName.current.getText(); }  
	 EOF 
;

// Rule QualifiedName
ruleQualifiedName returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(    this_ID_0=RULE_ID    {
		$current.merge(this_ID_0);
    }

    { 
    newLeafNode(this_ID_0, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_0()); 
    }
(((
	'.' 
)=>
	kw='.' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedNameAccess().getFullStopKeyword_1_0()); 
    }
)    this_ID_2=RULE_ID    {
		$current.merge(this_ID_2);
    }

    { 
    newLeafNode(this_ID_2, grammarAccess.getQualifiedNameAccess().getIDTerminalRuleCall_1_1()); 
    }
)*)
    ;





// Entry rule entryRuleQualifiedNameWithWildcard
entryRuleQualifiedNameWithWildcard returns [String current=null] 
	:
	{ newCompositeNode(grammarAccess.getQualifiedNameWithWildcardRule()); } 
	 iv_ruleQualifiedNameWithWildcard=ruleQualifiedNameWithWildcard 
	 { $current=$iv_ruleQualifiedNameWithWildcard.current.getText(); }  
	 EOF 
;

// Rule QualifiedNameWithWildcard
ruleQualifiedNameWithWildcard returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] 
    @init { enterRule(); 
    }
    @after { leaveRule(); }:
(
    { 
        newCompositeNode(grammarAccess.getQualifiedNameWithWildcardAccess().getQualifiedNameParserRuleCall_0()); 
    }
    this_QualifiedName_0=ruleQualifiedName    {
		$current.merge(this_QualifiedName_0);
    }

    { 
        afterParserOrEnumRuleCall();
    }
(
	kw='.' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedNameWithWildcardAccess().getFullStopKeyword_1_0()); 
    }

	kw='*' 
    {
        $current.merge(kw);
        newLeafNode(kw, grammarAccess.getQualifiedNameWithWildcardAccess().getAsteriskKeyword_1_1()); 
    }
)?)
    ;





// Rule PropertyModifier
rulePropertyModifier returns [Enumerator current=null] 
    @init { enterRule(); }
    @after { leaveRule(); }:
((	enumLiteral_0='transient' 
	{
        $current = grammarAccess.getPropertyModifierAccess().getTRANSIENTEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_0, grammarAccess.getPropertyModifierAccess().getTRANSIENTEnumLiteralDeclaration_0()); 
    }
)
    |(	enumLiteral_1='auto-increment' 
	{
        $current = grammarAccess.getPropertyModifierAccess().getINCREMENTEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
        newLeafNode(enumLiteral_1, grammarAccess.getPropertyModifierAccess().getINCREMENTEnumLiteralDeclaration_1()); 
    }
));



fragment RULE_NUMBER : '0'..'9';

RULE_INT : '-'? RULE_NUMBER+;

RULE_FLOAT : ('-'? RULE_NUMBER+ '.' RULE_NUMBER* (('e'|'E') ('+'|'-')? RULE_NUMBER+)? 'f'?|'-'? RULE_NUMBER+ 'f');

RULE_BOOLEAN : ('true'|'false');

RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

RULE_STRING : ('"' ('\\' .|~(('\\'|'"')))* '"'|'\'' ('\\' .|~(('\\'|'\'')))* '\'');

RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

RULE_WS : (' '|'\t'|'\r'|'\n')+;

RULE_ANY_OTHER : .;


