class CreateCauseOrganisations < ActiveRecord::Migration
  def self.up
    create_table :cause_organisations do |t|
      t.integer :cause_id
      t.integer :organisation_id

      t.timestamps
    end
  end

  def self.down
    drop_table :cause_organisations
  end
end
