ActiveRecord::Base.class_eval do

  # todo
  def self.validates_max_entries_for( *attr_names )
    configuration = { :case_sensitive => true }
    configuration.update(attr_names.extract_options!)
    validates_each(attr_names, configuration) do |record, attr_name, value|
      puts record
      puts attr_name
      puts value
      puts configuration
    end
  end
end