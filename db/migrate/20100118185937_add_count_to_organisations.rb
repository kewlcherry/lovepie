class AddCountToOrganisations < ActiveRecord::Migration
  def self.up
    add_column :organisations, :causes_count, :integer
  end

  def self.down
    remove_column :organisations, :causes_count
  end
end
