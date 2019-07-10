const DataHub = require("/data-hub/5/datahub.sjs");
const datahub = new DataHub();
const dcReferencedBy = "http://purl.org/dc/terms/isReferencedBy";

function main(content, options) {

  // Get the document's ID/URI.
  let id = content.uri;

  // Get and manipulate the context metadata associated with the document.
  let context = content.context;

  // Set the format of the output.
  let outputFormat = options.outputFormat ? options.outputFormat.toLowerCase() : datahub.flow.consts.DEFAULT_FORMAT;

  // Verify that the output is in either XML or JSON format, not in binary or other text format.
  if (outputFormat !== datahub.flow.consts.JSON && outputFormat !== datahub.flow.consts.XML) {
    datahub.debug.log({
      message: 'The output format of type ' + outputFormat + ' is invalid. Valid options are ' + datahub.flow.consts.XML + ' or ' + datahub.flow.consts.JSON + '.',
      type: 'error'
    });
    throw Error('The output format of type ' + outputFormat + ' is invalid. Valid options are ' + datahub.flow.consts.XML + ' or ' + datahub.flow.consts.JSON + '.');
  }

  /*
  This scaffolding assumes that the document is retrieved from the source database.

  If you are adding information to (instead of modifying values in) an existing record in the database,
  you can create an instance (object), headers (object), and triples (array) using data from content.value,
  instead of calling the flowUtils functions to extract information from the document retrieved from MarkLogic Server.
  In addition, you do not have to verify that the document exists.

  Example code for using data that is sent to MarkLogic Server for the document
  let instance = content.value;
  let triples = [];
  let headers = {};
  */

  // Verify that the record is still in the database before processing it. Not required when creating new records.
  // 'fn' is a MarkLogic Server function.
  if (!fn.docAvailable(id)) {
    datahub.debug.log({message: 'The document with the uri: ' + id + ' could not be found.', type: 'error'});
    throw Error('The document with the uri: ' + id + ' could not be found.')
  }

  // Set the 'doc' variable to the value of 'content.value'.
  let doc = content.value;

  // If the document is an instance of Document or XMLDocument (i.e., not a type of Node, such as ObjectNode or XMLNode).
  if (doc && (doc instanceof Document || doc instanceof XMLDocument)) {
    doc = fn.head(doc.root);
  }

  // Get the instance. If the instance is not found, getInstance() returns an empty object.
  // If the document is inside an envelope, the default format is envelope/instance.
  // If you have a custom format, get the instance using: doc.xpath('/xpath/here')
  let instance = datahub.flow.flowUtils.getInstance(doc).toObject() || {};

  // Get the triples. Return null, if empty or not found.
  let triples = datahub.flow.flowUtils.getTriples(doc) || [];

  // Get the headers. Return null, if not found.
  let headers = datahub.flow.flowUtils.getHeaders(doc) || {};

  // To set attachments, use this instead: instance['$attachments'] = doc;
  instance['$attachments'] = {
    envelope: {
      headers: datahub.flow.flowUtils.getHeaders(doc) || {},
      triples: datahub.flow.flowUtils.getTriples(doc) || [],
      instance: datahub.flow.flowUtils.getInstance(doc)
    }
  };

  // Insert code to manipulate the instance, triples, headers, uri, context metadata, etc.
  
  /**
   * Facebook relationship building and denormalization by executing a search, relating document URIs and embedding Ad metadata within the asset.
   */
  if (instance.facebookCampaigns != null) {
    for (let count = 0; count < instance.facebookCampaigns.length; count++) {
      let campaignId = instance.facebookCampaigns[count];
      let query = cts.andQuery([
        cts.collectionQuery('AdImpressions'),
        cts.jsonPropertyValueQuery('type', 'Facebook'),
        cts.jsonPropertyValueQuery('id', campaignId)
      ]);
    
      let results = cts.search(query).toArray();
      if (results != null && results.length > 0) {
        let result = results[0];
        let uri = fn.baseUri(result);
        triples.push(sem.triple(sem.iri(id), sem.iri(dcReferencedBy), sem.iri(uri)));

        let analytic = result.root.envelope.instance;
        if (analytic != null) {
          instance.analytics = instance.analytics || {};
          instance.analytics['facebook'] = instance.analytics.facebook || {};
          instance.analytics.facebook[analytic.id] = analytic;
        }
      }
    }
  }
  /**
   * Twitter relationship building and denormalization by executing a search, relating document URIs and embedding Ad metadata within the asset.
   */
  if (instance.twitterCampaigns != null) {
    for (let count = 0; count < instance.twitterCampaigns.length; count++) {
      let campaignId = instance.twitterCampaigns[count];
      let query = cts.andQuery([
        cts.collectionQuery('AdImpressions'),
        cts.jsonPropertyValueQuery('type', 'Twitter'),
        cts.jsonPropertyValueQuery('id', campaignId)
      ]);
    
      let results = cts.search(query).toArray();
      if (results != null && results.length > 0) {
        let result = results[0];
        let uri = fn.baseUri(result);
        triples.push(sem.triple(sem.iri(id), sem.iri(dcReferencedBy), sem.iri(uri)));

        let analytic = result.root.envelope.instance;
        if (analytic != null) {
          instance.analytics = instance.analytics || {};
          instance.analytics['twitter'] = instance.analytics.twitter || {};
          instance.analytics.twitter[analytic.id] = analytic;
        }
      }
    }
  } 
  /**
   * Instagram relationship building and denormalization by executing a search, relating document URIs and embedding Ad metadata within the asset.
   */
  let query = cts.andQuery([
    cts.collectionQuery('AdImpressions'),
    cts.jsonPropertyValueQuery('type', 'Instagram'),
    cts.jsonPropertyValueQuery('assetId', instance.id)
  ]);

  let results = cts.search(query).toArray();
  if (results != null && results.length > 0) {
    let result = results[0];
    let uri = fn.baseUri(result);
    triples.push(sem.triple(sem.iri(id), sem.iri(dcReferencedBy), sem.iri(uri)));

    let analytic = result.root.envelope.instance;
    if (analytic != null) {
      instance.analytics = instance.analytics || {};
      instance.analytics['instagram'] = instance.analytics.instagram || {};
      instance.analytics.instagram[analytic.id] = analytic;
    }
  }

  // Create the envelope using the specified output format, and set the content.value to the envelope.
  let envelope = datahub.flow.flowUtils.makeEnvelope(instance, headers, triples, outputFormat);
  content.value = envelope;

  // Assign the URI. In this example, the URI is the same.
  content.uri = id;

  // Assign the context.
  content.context = context;

  // Return the content to be written.
  return content;
}

module.exports = {
  main: main
};
