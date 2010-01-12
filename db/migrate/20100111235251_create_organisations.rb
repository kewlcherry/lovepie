class CreateOrganisations < ActiveRecord::Migration
  def self.up
    create_table :organisations do |t|
      t.string :name
      t.string :paypal
      t.string :url
      t.boolean :active

      t.timestamps
    end
  end

  def self.down
    drop_table :organisations
  end
end