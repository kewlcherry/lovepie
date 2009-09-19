class AddColumnsToOrganisation < ActiveRecord::Migration
  def self.up
    add_column :organisations, :paypal_id, :string
    add_column :organisations, :logo, :string
    add_column :organisations, :desc, :string
  end

  def self.down
    remove_column :organisations, :paypal_id
    remove_column :organisations, :logo
    remove_column :organisations, :desc
  end
end
